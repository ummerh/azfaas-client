package com.jet.demo.faas.client.simple;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.jet.demo.faas.openid.demo.OpenIdConfiguration;
import com.jet.demo.faas.openid.demo.OpenIdToken;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Controller
@RequestMapping("/idp")
public class OpenIdController {

	private static final String KID123 = "kid123";
	// Prepare the RSA Key Pair
	private static RSAKey rsaJWK = null;
	private static RSAKey rsaPublicJWK = null;
	static {
		try {
			rsaJWK = new RSAKeyGenerator(2048).keyID(KID123).generate();
			rsaPublicJWK = rsaJWK.toPublicJWK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// FIXME this should be in a persistence storages
	private static HashMap<String, String> nonces = new HashMap<String, String>();
	private static HashMap<String, String> refresh_tokens = new HashMap<String, String>();

	@Value("${app.url}")
	private String appUrl;

	@RequestMapping(path = "/.well-known/openid-configuration", produces = "application/json")
	public @ResponseBody OpenIdConfiguration configuration(HttpServletRequest req) {
		return OpenIdConfiguration.DEFAULT(appUrl + "/idp");
	}

	@RequestMapping(path = "/oauth2/v2.0/authorize", method = { RequestMethod.GET, RequestMethod.POST })
	public RedirectView authorize(HttpServletRequest req, @RequestParam String client_id, @RequestParam String nonce,
			@RequestParam String response_type, @RequestParam String scope,
			@RequestParam(required = false, defaultValue = "login") String prompt, @RequestParam String redirect_uri,
			@RequestParam(required = false, defaultValue = "query") String response_mode,
			@RequestParam(required = false, defaultValue = "random") String state) throws Exception {
		// FIXME - Validate the inputs
		// code here

		// FIXME - this should be instead in a persistence store
		HttpSession session = req.getSession();
		session.setAttribute("client_id", client_id);
		session.setAttribute("nonce", nonce);
		session.setAttribute("response_type", response_type);
		session.setAttribute("scope", scope);
		session.setAttribute("redirect_uri", redirect_uri);
		session.setAttribute("response_mode", response_mode);
		session.setAttribute("state", state);
		return new RedirectView(appUrl + "/uilogin/#/user/login");
	}

	@RequestMapping("/oauth2/v2.0/logout")
	public RedirectView endSession(HttpServletRequest req, @RequestParam(required = false) String client_id,
			@RequestParam(required = false) String id_token_hint,
			@RequestParam(required = false) String post_logout_redirect_uri) throws Exception {
		req.getSession().invalidate();
		return new RedirectView(post_logout_redirect_uri);
	}

	@RequestMapping(path = "/oauth2/v2.0/token", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody OpenIdToken issueToken(@RequestParam String client_id, @RequestParam String client_secret,
			@RequestParam String code, @RequestParam String grant_type,
			@RequestParam(required = false, defaultValue = "openid") String scope, @RequestParam String redirect_uri)
			throws Exception {
		// FIXME validate the inputs
		String nonce = nonces.get(code);
		String refreshToken = refresh_tokens.get(code);
		OpenIdConfiguration config = OpenIdConfiguration.DEFAULT(appUrl + "/idp");

		// Create RSA-signer with the private key
		JWSSigner signer = new RSASSASigner(rsaJWK);
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().subject("demouser").issuer(config.getIssuer())
				.expirationTime(new Date(new Date().getTime() + (60 * 60 * 1000))).claim("ver", "1.0")
				.audience(client_id).claim("nonce", nonce).issueTime(new Date()).claim("idp_access_token", refreshToken)
				.claim("idp", "azfaas-idp").claim("name", "demouser").claim("oid", "demouser")
				.claim("given_name", "Demo User").claim("emails", "demouser@email.com").build();
		// create the signer
		SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build(),
				claimsSet);
		signedJWT.sign(signer);
		String jwtToken = signedJWT.serialize();
		System.out.println(jwtToken);
		return new OpenIdToken(new Date().getTime(), "Bearer", jwtToken, "openid", 3600, refreshToken, jwtToken);
	}

	@RequestMapping("/discovery/v2.0/keys")
	public @ResponseBody String getKeys() throws Exception {
		RSAKey.Builder builder = new RSAKey.Builder(rsaPublicJWK).keyUse(KeyUse.SIGNATURE).algorithm(JWSAlgorithm.RS256)
				.keyID(KID123);
		return new JWKSet(builder.build()).toString();
	}

	@RequestMapping(path = "/authorize", method = { RequestMethod.GET, RequestMethod.POST })
	public RedirectView authorize(HttpServletRequest req, @RequestParam String userName,
			@RequestParam String userPassword) throws Exception {
		HttpSession session = req.getSession();
		String code = UUID.randomUUID().toString();
		session.setAttribute("code", code);
		String refreshToken = UUID.randomUUID().toString();
		session.setAttribute("refresh_token", refreshToken);
		String client_id = session.getAttribute("client_id").toString();
		String nonce = session.getAttribute("nonce").toString();
		OpenIdConfiguration config = OpenIdConfiguration.DEFAULT(appUrl + "/idp");
		nonces.put(code, nonce);
		// Create RSA-signer with the private key
		JWSSigner signer = new RSASSASigner(rsaJWK);
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().subject("demouser").issuer(config.getIssuer())
				.expirationTime(new Date(new Date().getTime() + (60 * 60 * 1000))).claim("ver", "1.0")
				.audience(client_id).claim("nonce", nonce).issueTime(new Date()).claim("idp_access_token", refreshToken)
				.claim("idp", "azfaas-idp").claim("name", "demouser").claim("oid", "demouser")
				.claim("given_name", "Demo User").claim("emails", "demouser@email.com").build();
		// create the signer
		SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build(),
				claimsSet);
		signedJWT.sign(signer);
		String jwtToken = signedJWT.serialize();
		refresh_tokens.put(code, refreshToken);
		return new RedirectView(session.getAttribute("redirect_uri") + "?code=" + jwtToken + "&id_token=" + jwtToken
				+ "&state=" + session.getAttribute("state"));
	}

}
