package controller.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.Util;

/**
 * Session filter
 */
@WebFilter("/*")
public class SessionFilter implements Filter {

	/**
	 * Filter method. Checks if session holds needed information about current user, if so - do nothing,
	 * otherwise stores in session guest information 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if (session.getAttribute("currentUserId") == null) {
			session.setAttribute("currentUserId", 0);
			session.setAttribute("currentUserType", "guest");
			session.setAttribute("bookEditVis", "none");
			session.setAttribute("bookOrderVis", "none");
			session.setAttribute("isBlocked", Boolean.FALSE);
			Locale locale = Util.defineLocale(req);
			session.setAttribute("locale", locale);
		}

		chain.doFilter(request, response);
	}

}
