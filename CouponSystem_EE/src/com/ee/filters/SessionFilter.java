package com.ee.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * The session filter validates that the user has an active session, that The
 * session has a logged in user (“FACADE” attribute) and that this User has
 * passes authentication. If all three requirement are met then the filter will
 * pass the user into the relevant service. If one or more of the above demands
 * are not met then the Session Filter will redirect the user to the login page
 * (Login Service).
 */

// @WebFilter("/SessionFilter")
public class SessionFilter extends HttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String LOGIN_URL = "../../index.html";

	public SessionFilter() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		ServletContext servContext = request.getServletContext();

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);

		// if (req.getRequestURI().contains(LOGIN_URL)) // if in "index.html" then no need for
		// the filter to kick in
		// chain.doFilter(req, resp);

		if (session == null) {
			System.out.println("INFO: No Active Session Found!");
//			((HttpServletResponse) resp).sendRedirect(servContext.getContextPath() + LOGIN_URL);
			resp.sendError(403);
			return;
		}
		if (session.getAttribute("FACADE") == null) {
			System.out.println("INFO: No logged In user. access denied!");
//			((HttpServletResponse) resp).sendRedirect(servContext.getContextPath() + LOGIN_URL);
			resp.sendError(403);
			return;
		}
		if (session != null) {
			if (session.getAttribute("AUTHENTICATED") != null && session.getAttribute("FACADE") != null) {
				System.out.println("INFO:" + session.getAttribute("FACADE") + " is already logged In");
				chain.doFilter(req, resp);
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}


	public void destroy() {
		// TODO Auto-generated method stub
	}

}
