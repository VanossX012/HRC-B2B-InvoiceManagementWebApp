package com.highradius.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.highradius.model.*;
import com.highradius.implementation.UserDao;
import com.highradius.implementation.UserDaoImpl;

@WebServlet("/Fetchallusers")
public class Fetchallusers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
       
    public Fetchallusers() {
        userDao = new UserDaoImpl();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonResponse = new String();
		PrintWriter out = response.getWriter();
		System.out.println();
		System.out.println("1");
		try {
            // Retrieve all invoices from the database
            List<Pojo> invoices = userDao.selectAllUsers();
            System.out.println("2");

            // Convert invoices to JSON
            Gson gson = new Gson();
            jsonResponse = gson.toJson(invoices);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
//        System.out.println(jsonResponse);
        out.print(jsonResponse);
        out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
