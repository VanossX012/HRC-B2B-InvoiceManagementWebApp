package com.highradius.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import com.highradius.implementation.*;
import com.highradius.model.Pojo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	PreparedStatement pst;
       
    public AddUser() {
        super();
        userDao = new UserDaoImpl();
        System.out.println("1");
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET, POST");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        System.out.println("2");
        int sINo = Integer.parseInt(request.getParameter("Sl_No"));
		int sales_org = Integer.parseInt(request.getParameter("SALES_ORG"));
		System.out.println("3");
		int cUSTOMER_ORDER_ID = Integer.parseInt(request.getParameter("CUSTOMER_ORDER_ID"));
		int cUSTOMER_NUMBER = Integer.parseInt(request.getParameter("CUSTOMER_NUMBER"));
		int cOMPANY_CODE = Integer.parseInt(request.getParameter("COMPANY_CODE"));
		String dISTRIBUTION_CHANNEL = request.getParameter("DISTRIBUTION_CHANNEL");
		String orderDate=request.getParameter("ORDER_CREATION_DATE");
		String oRDER_CURRENCY = request.getParameter("ORDER_CURRENCY");
		double oRDER_AMOUNT = Double.parseDouble(request.getParameter("ORDER_AMOUNT"));
		double aMOUNT_IN_USD = Double.parseDouble(request.getParameter("AMOUNT_IN_USD"));
		Pojo newUser = new Pojo(sINo,cUSTOMER_ORDER_ID,sales_org,dISTRIBUTION_CHANNEL,cOMPANY_CODE,orderDate,oRDER_AMOUNT,
				oRDER_CURRENCY,cUSTOMER_NUMBER,aMOUNT_IN_USD);
		try {
			userDao.insertUser(newUser);
			out.println("<font color='red' style='Verdana'>  Record Added Successfully  </font>");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
