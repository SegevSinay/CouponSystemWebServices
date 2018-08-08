package com.ee.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ee.utilities.LoginUser;

/**
 * The Logout Service invalidate any active session since if the user asked for
 * re-login and/or been passed by the Session Filter then It means that
 * something went wrong with the current session.
 */

@Path("/LogoutService")
public class LogoutService {

	public LogoutService() {
	}

	@Path("/Logout")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response Logout(@Context HttpServletRequest request, LoginUser loginUser) {
		try {
			HttpSession session = request.getSession(false);
			session.invalidate();
			return Response.status(Response.Status.OK).type(MediaType.TEXT_PLAIN).entity("GoodBye!").build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN)
					.entity("Error while logging out!" + e.getMessage()).build();
		}
	}
}
