package com.ee.utilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.exceptions.CouponSystemException;
import com.main.CouponSystem;

/**
 * Servlet implementation class SysStartUp
 */
@WebServlet()
public class SysStartUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		try {
			CouponSystem cs= CouponSystem.getInstance();
			System.out.println("Coupon System has been Activated");
			getServletContext().setAttribute("System", cs);
		} catch (CouponSystemException | InterruptedException e) {
		
		}
	}
       
  

}
