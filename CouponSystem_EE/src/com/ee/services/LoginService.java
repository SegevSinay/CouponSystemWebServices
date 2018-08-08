package com.ee.services;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ee.utilities.LoginUser;
import com.exceptions.CouponSystemException;
import com.facade.CouponClientFacade;
import com.main.ClientType;
import com.main.CouponSystem;

/**
 * The Login Service invalidate any active session since if the user asked for
 * re-login and/or been passed by the Session Filter then It means that
 * something went wrong with the current session. The login service perform an
 * authentication validation according to the Html form data that was passed to
 * it. If all credential are correct (validation is done via CS engine Login
 * method) then the Login Service will create a session for the user and will
 * load the session with the following: (a) Session Timeout timer: determents
 * the user inactive time before session will invalidate. (b) “FACADE”
 * attribute: loads the relevant Coupon Client Facade on the session. (c)
 * “AUTHENTICATED” attribute (Boolean): ‘true’ if successfully passes
 * authentication. (d) “ClientType” attribute: Client Type, needed for Angular
 * login component.
 * 
 * If the Login was successful, the user will be passed to the relevant service
 */
@Path("LoginService")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginService() {
	}

	@POST
	@Path("/Login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request, LoginUser loginUser) {
		System.out.println(loginUser);
		HttpSession session = null;
		try {
			request.getSession().invalidate(); // Invalidates previous session on new login attempt.

			String username = loginUser.getUsername();
			System.out.println(username);
			String password = loginUser.getPassword();
			System.out.println(password);

			ClientType clientType = null;
			System.out.println(loginUser.getClientType());
			if (loginUser.getClientType() != null) {
				switch (loginUser.getClientType()) {
				case "ADMIN":
					clientType = ClientType.ADMIN;// from String to ENUM
					break;
				case "COMPANY":
					clientType = ClientType.COMPANY;// from String to ENUM
					break;
				case "CUSTOMER":
					clientType = ClientType.CUSTOMER;// from String to ENUM
					break;
				}
			}

			System.out.println("INFO: the Following user is trying to login : \nusername=" + username + "\npassword="
					+ password + "\ntype=" + clientType);

			CouponClientFacade ccf = null;

			try {
				CouponSystem cs = CouponSystem.getInstance();
				ccf = cs.login(username, password, clientType);

				System.out.println(ccf);
			} catch (CouponSystemException | InterruptedException e) {
				System.out.println("ERROR: login Failed!" + "\ncause: " + e.getMessage());
				return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN)
						.entity("Login Failed! Please Try Again." + e.getMessage()).build();
			}
			if (ccf == null) {
			} else {
				System.out.println("INFO: User '" + loginUser.getUsername() + "' has logged in sucessfully");
				session = ((HttpServletRequest) request).getSession(true);
				System.out.println("INFO: session:/' " + session + "/' has been created");
				session.setMaxInactiveInterval(60*10); // 60*10 each session will last 10 minutes before timeout
				session.setAttribute("FACADE", ccf);
				session.setAttribute("AUTHENTICATED", true);
				System.out.println(session.getAttribute("FACADE"));
				session.setAttribute("clientType", loginUser.getClientType());

			}
			System.out.println("INFO: session:/' " + session + "/' has been passed on");
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(loginUser).build();

		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN)
					.entity("Login Failed! Please Try Again." + e.getMessage()).build();
		}
	}
}
