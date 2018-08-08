package com.ee.services;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.beans.Coupon;
import com.beans.CouponType;
import com.exceptions.CouponSystemException;
import com.facade.CompanyFacade;

/**
 * Contains all CRUD operations service for the company coupons.
 */
@Path("service/company")
public class CompanyServices {

	/**
	 * calls the coupon system company facade createCoupon(coupon) method in order
	 * to create a coupon.
	 */
	@POST
	@Path("/createCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/company/createCoupon
	public Response createCoupon(@Context HttpServletRequest request, Coupon coupon) throws CouponSystemException {
		try {
			CompanyFacade compf = (CompanyFacade) request.getSession(false).getAttribute("FACADE");
			compf.createCoupon(coupon);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(coupon).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system company facade removeCoupon(coupon) method in order
	 * to delete an existing coupon. since @DELETE does not pass a body, I had to
	 * use @POST as a work around.
	 */
	@POST
	@Path("/removeCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/company/removeCoupon
	public Response removeCoupon(@Context HttpServletRequest request, Coupon coupon)
			throws CouponSystemException, InterruptedException {
		try {
			CompanyFacade compf = (CompanyFacade) request.getSession(false).getAttribute("FACADE");
			compf.removeCoupon(coupon);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(coupon).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system company facade updateCoupon(coupon) method in order
	 * to update an existing coupon.
	 */
	@PUT
	@Path("/updateCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/company/updateCoupon
	public Response updateCoupon(@Context HttpServletRequest request, Coupon coupon) throws CouponSystemException {
		try {
			CompanyFacade compf = (CompanyFacade) request.getSession(false).getAttribute("FACADE");
			compf.updateCoupon(coupon);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(coupon).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * NO IN USE: REPLACED BY 'getCouponAfterOwnershipValidation(...) method'.
	 */
	@GET
	@Path("/getCoupon/NoValidation/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/company/getCoupon/{id}
	public Response getCoupon(@Context HttpServletRequest request, @PathParam("id") int id)
			throws CouponSystemException {
		try {
			CompanyFacade compf = (CompanyFacade) request.getSession(false).getAttribute("FACADE");
			Coupon coupon = compf.getCoupon(id);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(coupon).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system company facade getCoupon(id) method in order to
	 * retrieve a specific coupon data.
	 */
	@GET
	@Path("/getCoupon/{coupId}")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/company/getCoupon/{id}
	public Response getCouponAfterOwnershipValidation(@Context HttpServletRequest request,
			@PathParam("coupId") int coupId) throws CouponSystemException {
		try {
			CompanyFacade compf = (CompanyFacade) request.getSession(false).getAttribute("FACADE");
			long compId = compf.getLoginCompany().getId();
			Coupon coupon = compf.getCouponAfterOwnershipValidation(compId, coupId);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(coupon).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system company facade allCoupons() method in order to
	 * retrieve all coupons data.
	 */
	@GET
	@Path("/getAllCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/company/getAllCoupons
	public Response getAllCoupons(@Context HttpServletRequest request) throws CouponSystemException {
		try {
			CompanyFacade compf = (CompanyFacade) request.getSession(false).getAttribute("FACADE");
			Collection<Coupon> allCoupons = compf.getAllCoupons();
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(allCoupons.toArray(new Coupon[0])).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system company facade getAllCouponsByType(type) method in
	 * order to retrieve all coupons data by a specific coupon type.
	 */
	@GET
	@Path("/getAllCoupons/bytype/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/company/getAllCoupons/bytype/{type}
	public Response getAllCouponsByType(@Context HttpServletRequest request, @PathParam("type") CouponType type)
			throws CouponSystemException {
		try {
			CompanyFacade compf = (CompanyFacade) request.getSession(false).getAttribute("FACADE");
			Collection<Coupon> allCouponsByType = compf.getAllCouponsByType(type);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(allCouponsByType.toArray(new Coupon[0])).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}
}
