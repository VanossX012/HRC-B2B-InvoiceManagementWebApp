package com.highradius.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.*;
import com.highradius.model.Pojo;


@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
       
    
    public UpdateUser() {
        super();
        userDao = new UserDaoImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		int sINo = Integer.parseInt(request.getParameter("Sl_No"));
		int sales_org = Integer.parseInt(request.getParameter("SALES_ORG"));
		System.out.println("3");
		int cUSTOMER_ORDER_ID = Integer.parseInt(request.getParameter("CUSTOMER_ORDER_ID"));
		int cUSTOMER_NUMBER = Integer.parseInt(request.getParameter("CUSTOMER_NUMBER"));
		int cOMPANY_CODE = Integer.parseInt(request.getParameter("COMPANY_CODE"));
		String dISTRIBUTION_CHANNEL = request.getParameter("DISTRIBUTION_CHANNEL");
		String oRDER_CREATION_DATE = request.getParameter("ORDER_CREATION_DATE");
		String oRDER_CURRENCY = request.getParameter("ORDER_CURRENCY");
		double oRDER_AMOUNT = Double.parseDouble(request.getParameter("ORDER_AMOUNT"));
		double aMOUNT_IN_USD = Double.parseDouble(request.getParameter("AMOUNT_IN_USD"));
		Pojo newUser = new Pojo(sINo,cUSTOMER_ORDER_ID,sales_org,dISTRIBUTION_CHANNEL,cOMPANY_CODE,oRDER_CREATION_DATE,oRDER_AMOUNT,
				oRDER_CURRENCY,cUSTOMER_NUMBER,aMOUNT_IN_USD);
		try {
			userDao.updateUser(newUser);
			out.write("Record Modified Successfully");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
		
	

}
