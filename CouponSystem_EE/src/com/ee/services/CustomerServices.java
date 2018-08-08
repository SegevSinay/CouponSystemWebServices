package com.ee.services;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.beans.Coupon;
import com.beans.CouponType;
import com.exceptions.CouponSystemException;
import com.facade.CustomerFacade;

/*
 * Contains the coupon store Customer services – reviewing all coupons, 
 * purchase coupons and reviewing Customer’s purchasing history.
 **/
@Path("service/customer")
public class CustomerServices {

	/**
	 * calls the coupon system customer facade purchaseCoupon() method in order to
	 * perform coupon purchasing.
	 */
	@POST
	@Path("/purchaseCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/customer/purchaseCoupon
	public Response purchaseCoupon(@Context HttpServletRequest request, Coupon coupon) throws CouponSystemException {

		try {
			CustomerFacade custf = (CustomerFacade) request.getSession(false).getAttribute("FACADE");
			custf.purchaseCoupon(coupon);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(coupon).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system customer facade getAllPurchasedCoupon() method in
	 * order to retrieve all purchase coupon data
	 */
	@GET
	@Path("/getAllPurchasedCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/customer/allPurchasedCoupons
	public Response getAllPurchasedCoupons(@Context HttpServletRequest request) throws CouponSystemException {
		try {
			CustomerFacade custf = (CustomerFacade) request.getSession(false).getAttribute("FACADE");
			Collection<Coupon> allPurchasedCoupons = custf.getAllPurchasedCoupons();
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(allPurchasedCoupons.toArray(new Coupon[0])).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system customer facade getAllPurchasedCouponsByType(type) method in
	 * order to retrieve all purchased coupons by a specific coupon type.
	 */
	@GET
	@Path("/getAllPurchasedCoupons/bytype/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/customer/allPurchasedCoupons/byType/{type}
	public Response getAllPurchasedCouponsByType(@Context HttpServletRequest request,
			@PathParam("type") CouponType type) throws CouponSystemException {
		try {
			CustomerFacade custf = (CustomerFacade) request.getSession(false).getAttribute("FACADE");
			Collection<Coupon> allPurchasedCouponsByType = custf.getAllPurchasedCouponsByType(type);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(allPurchasedCouponsByType.toArray(new Coupon[0])).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system customer facade getAllPurchasedCouponsByPrice(price) method in
	 * order to retrieve all purchased coupons by a max price limit.
	 */
	@GET
	@Path("/getAllPurchasedCoupons/byprice/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/customer/allPurchasedCoupons/{price}
	public Response getAllPurchasedCouponsByPrice(@Context HttpServletRequest request, @PathParam("price") double price)
			throws CouponSystemException {
		try {
			CustomerFacade custf = (CustomerFacade) request.getSession(false).getAttribute("FACADE");
			Collection<Coupon> allPurchasedCouponsByPrice = custf.getAllPurchasedCouponsByPrice(price);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(allPurchasedCouponsByPrice.toArray(new Coupon[0])).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system customer facade getAllCoupons() method in
	 * order to retrieve all coupons.
	 */
	@GET
	@Path("/getAllCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/customer/getAllCoupons
	public Response getAllCoupons(@Context HttpServletRequest request) throws CouponSystemException {
		try {
			CustomerFacade custf = (CustomerFacade) request.getSession(false).getAttribute("FACADE");
			Collection<Coupon> allCoupons = custf.getAllCoupons();
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(allCoupons.toArray(new Coupon[0])).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system customer facade getAllCoupons() method in
	 * order to retrieve all coupons.
	 */
	@GET
	@Path("/getAllCoupons/bytype/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/customer/getAllCoupons
	public Response getAllCouponsByType(@Context HttpServletRequest request, @PathParam("type") CouponType type)
			throws CouponSystemException {
		try {
			CustomerFacade custf = (CustomerFacade) request.getSession(false).getAttribute("FACADE");
			Collection<Coupon> allCouponsByType = custf.getAllCouponsByType(type);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(allCouponsByType.toArray(new Coupon[0])).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system customer facade getAllCoupons() method in
	 * order to retrieve all coupons by max price limit.
	 */
	@GET
	@Path("/getAllCoupons/byprice/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/customer/getAllCouponsByPrice
	public Response getAllCouponsByPrice(@Context HttpServletRequest request, @PathParam("price") double price)
			throws CouponSystemException {
		try {
			CustomerFacade custf = (CustomerFacade) request.getSession(false).getAttribute("FACADE");
			Collection<Coupon> allCouponsByPrice = custf.getAllCouponsByPrice(price);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(allCouponsByPrice.toArray(new Coupon[0])).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}
}
