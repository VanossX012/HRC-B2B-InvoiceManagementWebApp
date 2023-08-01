package com.highradius.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class DbConnection {
	public static Connection connect() {
		Connection con = null;
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","6331");
			}
				catch (SQLException e) {
				e.printStackTrace();
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return con;
		
				
	}
}
