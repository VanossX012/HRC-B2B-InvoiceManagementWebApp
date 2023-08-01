package com.highradius.servlets;

import com.highradius.implementation.UserDao;
import com.highradius.implementation.UserDaoImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDAO;
    

    public void init() {
        userDAO = new UserDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
        int userId = Integer.parseInt(request.getParameter("Sl_No"));

        try {
            userDAO.deleteUser(userId);
            out.println("<font color='red' style='Verdana'>  Record Deleted Successfully  </font>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}