package com.jet.demo.faas.client.simple;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView home(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("home");
		if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (OidcUser.class.isAssignableFrom(principal.getClass())) {
				OidcUser oidUser = (OidcUser) principal;
				String givenName = oidUser.getClaimAsString("name");
				mv.addObject("USER_GIVEN_NAME", givenName);
				req.getSession().setAttribute("USER_GIVEN_NAME", givenName);
			}
		}
		return mv;
	}

	@Autowired
	@RequestMapping(value = "/oauth2/code/azure", method = RequestMethod.POST)
	public ModelAndView kioskPost() {
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}

}
