package com.jet.demo.faas.openid.demo;

public class OpenIdConfiguration {
	protected String issuer, authorization_endpoint, token_endpoint, end_session_endpoint, jwks_uri;
	protected String[] response_modes_supported, response_types_supported, scopes_supported, subject_types_supported,
			id_token_signing_alg_values_supported, token_endpoint_auth_methods_supported, claims_supported;

	public static OpenIdConfiguration DEFAULT(String baseUrl) {
		OpenIdConfiguration config = new OpenIdConfiguration();
		config.issuer = baseUrl + "/idp";
		config.authorization_endpoint = baseUrl + "/oauth2/v2.0/authorize";
		config.end_session_endpoint = baseUrl + "/oauth2/v2.0/logout";
		config.token_endpoint = baseUrl + "/oauth2/v2.0/token";
		config.jwks_uri = baseUrl + "/discovery/v2.0/keys";
		config.response_modes_supported = new String[] { "query", "fragment", "form_post" };
		config.response_types_supported = new String[] { "code", "code id_token" };
		config.scopes_supported = new String[] { "openid" };
		config.subject_types_supported = new String[] { "pairwise" };
		config.id_token_signing_alg_values_supported = new String[] { "RS256" };
		config.token_endpoint_auth_methods_supported = new String[] { "client_secret_post" };
		config.claims_supported = new String[] { "idp", "emails", "idp_access_token", "oid", "sub", "name",
				"given_name", "tfp", "iss", "iat", "exp", "aud", "acr", "nonce", "auth_time" };
		return config;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getAuthorization_endpoint() {
		return authorization_endpoint;
	}

	public void setAuthorization_endpoint(String authorization_endpoint) {
		this.authorization_endpoint = authorization_endpoint;
	}

	public String getToken_endpoint() {
		return token_endpoint;
	}

	public void setToken_endpoint(String token_endpoint) {
		this.token_endpoint = token_endpoint;
	}

	public String getEnd_session_endpoint() {
		return end_session_endpoint;
	}

	public void setEnd_session_endpoint(String end_session_endpoint) {
		this.end_session_endpoint = end_session_endpoint;
	}

	public String getJwks_uri() {
		return jwks_uri;
	}

	public void setJwks_uri(String jwks_uri) {
		this.jwks_uri = jwks_uri;
	}

	public String[] getResponse_modes_supported() {
		return response_modes_supported;
	}

	public void setResponse_modes_supported(String[] response_modes_supported) {
		this.response_modes_supported = response_modes_supported;
	}

	public String[] getResponse_types_supported() {
		return response_types_supported;
	}

	public void setResponse_types_supported(String[] response_types_supported) {
		this.response_types_supported = response_types_supported;
	}

	public String[] getScopes_supported() {
		return scopes_supported;
	}

	public void setScopes_supported(String[] scopes_supported) {
		this.scopes_supported = scopes_supported;
	}

	public String[] getSubject_types_supported() {
		return subject_types_supported;
	}

	public void setSubject_types_supported(String[] subject_types_supported) {
		this.subject_types_supported = subject_types_supported;
	}

	public String[] getId_token_signing_alg_values_supported() {
		return id_token_signing_alg_values_supported;
	}

	public void setId_token_signing_alg_values_supported(String[] id_token_signing_alg_values_supported) {
		this.id_token_signing_alg_values_supported = id_token_signing_alg_values_supported;
	}

	public String[] getToken_endpoint_auth_methods_supported() {
		return token_endpoint_auth_methods_supported;
	}

	public void setToken_endpoint_auth_methods_supported(String[] token_endpoint_auth_methods_supported) {
		this.token_endpoint_auth_methods_supported = token_endpoint_auth_methods_supported;
	}

	public String[] getClaims_supported() {
		return claims_supported;
	}

	public void setClaims_supported(String[] claims_supported) {
		this.claims_supported = claims_supported;
	}

}
