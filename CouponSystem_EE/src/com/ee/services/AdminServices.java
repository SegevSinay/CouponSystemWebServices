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

import com.beans.Company;
import com.beans.Customer;
import com.exceptions.CouponSystemException;
import com.facade.AdminFacade;

/**
 * Contains all CRUD operations service for the Customer/Company client.
 * 
 * The ADMIN credentials are: (a) Username: ‘admin’ (b) Password: ‘1234’ (c)
 * Client Type: ‘ADMIN’
 * 
 * Unless the ADMIN will create Customer/Company clients, the only service that
 * will be available in the website will be the ADMIN Service.
 */

@Path("service/admin")
public class AdminServices {

	@Context
	private HttpServletRequest request;

	/**
	 * calls the coupon system admin facade createCompany(company) method in order to
	 * create a company.
	 */
	@POST
	@Path("createCompany")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/admin/createCompany
	public Response createCompany(@Context HttpServletRequest request, Company company) {
		try {
			AdminFacade adminf = (AdminFacade) request.getSession(false).getAttribute("FACADE");
			System.out.println(adminf);
			adminf.createCompany(company);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(company).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system admin facade removeCompany(company) method in order to
	 * delete an existing company. since @DELETE does not pass a body, I had to
	 * use @POST as a work around.
	 */
	@POST
	@Path("/removeCompany")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/admin/removeCompany
	public Response removeCompany(@Context HttpServletRequest request, Company company) {
		try {
			AdminFacade adminf = (AdminFacade) request.getSession(false).getAttribute("FACADE");
			adminf.removeCompany(company);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(company).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system admin facade update company method in order to
	 * update an existing company.
	 */
	@PUT
	@Path("/updateCompany")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/admin/updateCompany
	public Response updateCompany(@Context HttpServletRequest request, Company company) {
		try {
			AdminFacade adminf = (AdminFacade) request.getSession(false).getAttribute("FACADE");
			adminf.updateCompany(company);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(company).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system admin facade getCompany(id) method in order to retrieve a specific company data
	 */
	@GET
	@Path("/getCompany/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/admin/getCompany/{id}
	public Response getCompany(@Context HttpServletRequest request, @PathParam("id") long id)
			throws CouponSystemException, InterruptedException {
		try {
			AdminFacade adminf = (AdminFacade) request.getSession(false).getAttribute("FACADE");
			Company company = adminf.getCompany(id);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(company).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system admin facade getAllCompanies() method in order to retrieve all companies data
	 */
	@GET
	@Path("/getAllCompanies")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/admin/getAllCompanies
	public Response getAllCompanies(@Context HttpServletRequest request) throws CouponSystemException {
		try {
			AdminFacade adminf = (AdminFacade) request.getSession(false).getAttribute("FACADE");
			Collection<Company> allCompanies = adminf.getAllCompanies();
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(allCompanies.toArray(new Company[0])).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupons system admin facade createCustomer(customer) method in order to
	 * create a customer.
	 */
	@POST
	@Path("/createCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/admin/createCustomer
	public Response createCustomer(@Context HttpServletRequest request, Customer customer)
			throws CouponSystemException {
		try {
			AdminFacade adminf = (AdminFacade) request.getSession(false).getAttribute("FACADE");
			adminf.createCustomer(customer);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(customer).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system admin facade deleteCustomer(customer) method in order to
	 * delete an existing company. since @DELETE does not pass a body, we had to
	 * use @POST as a work around.
	 */
	@POST
	@Path("/removeCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/admin/removeCustomer
	public Response removeCustomer(@Context HttpServletRequest request, Customer customer)
			throws CouponSystemException {
		try {
			AdminFacade adminf = (AdminFacade) request.getSession(false).getAttribute("FACADE");
			adminf.removeCustomer(customer);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(customer).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}
	/**
	 * calls the coupon system admin facade update customer method in order to
	 * update an existing customer.
	 */
	@PUT
	@Path("/updateCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/admin/updateCustomer
	public Response updateCustomer(@Context HttpServletRequest request, Customer customer)
			throws CouponSystemException {
		try {
			AdminFacade adminf = (AdminFacade) request.getSession(false).getAttribute("FACADE");
			adminf.updateCustomer(customer);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(customer).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}

	/**
	 * calls the coupon system admin facade getCustomer(id) method in order to retrieve a specific customer data
	 */
	@GET
	@Path("/getCustomer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/admin/getCustomer/{id}
	public Response getCustomer(@Context HttpServletRequest request, @PathParam("id") long id)
			throws CouponSystemException {
		try {
			AdminFacade adminf = (AdminFacade) request.getSession(false).getAttribute("FACADE");
			Customer customer = adminf.getCustomer(id);
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(customer).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}
	
	/**
	 * calls the coupon system admin facade getAllCustomers() method in order to retrieve all customers data
	 */
	@GET
	@Path("/getAllCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/CouponSystem_EE/rest/service/admin/getAllCustomers
	public Response getAllCustomers(@Context HttpServletRequest request)
			throws CouponSystemException, InterruptedException {
		try {
			AdminFacade adminf = (AdminFacade) request.getSession(false).getAttribute("FACADE");
			Collection<Customer> allCustomers = adminf.getAllCustomers();
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(allCustomers.toArray(new Customer[0])).build();
		} catch (CouponSystemException e) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage())
					.build();
		}
	}
}
