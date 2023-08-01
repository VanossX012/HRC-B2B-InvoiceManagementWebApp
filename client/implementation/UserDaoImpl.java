package com.highradius.implementation;

import com.highradius.connection.DbConnection;
import com.highradius.model.Pojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao{
	
	
	private static final String INSERT_USERS_SQL = "INSERT INTO h2h_oap" + "  (Sl_No,CUSTOMER_ORDER_ID,SALES_ORG,DISTRIBUTION_CHANNEL,COMPANY_CODE,ORDER_CREATION_DATE,ORDER_AMOUNT,ORDER_CURRENCY,CUSTOMER_NUMBER,AMOUNT_IN_USD) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	//private static final String SELECT_USER_BY_ID = "select * from h2h_oap where SI_no =?";
	private static final String SELECT_ALL_USERS = "SELECT Sl_no,CUSTOMER_ORDER_ID,SALES_ORG,DISTRIBUTION_CHANNEL,COMPANY_CODE,ORDER_CREATION_DATE,ORDER_AMOUNT,ORDER_CURRENCY,CUSTOMER_NUMBER,AMOUNT_IN_USD FROM h2h_oap ORDER BY Sl_No limit 100";
	private static final String DELETE_USERS_SQL = "delete from h2h_oap where Sl_no = ?;";
	private static final String UPDATE_USERS_SQL = "update h2h_oap set CUSTOMER_ORDER_ID = ?,SALES_ORG= ?,DISTRIBUTION_CHANNEL=?,COMPANY_CODE=?,ORDER_CREATION_DATE=?,ORDER_AMOUNT=?,ORDER_CURRENCY=?,CUSTOMER_NUMBER=?,AMOUNT_IN_USD=? where Sl_No = ?;";
	
	
	//ADD DATA
	 @Override
	public void insertUser(Pojo user) throws SQLException {
		try (Connection connection = DbConnection.connect();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);){
			

			preparedStatement.setInt(1, user.getSI_No());
			preparedStatement.setInt(2, user.getCustomerOrderId());
			preparedStatement.setInt(3, user.getSalesOrg());
			preparedStatement.setString(4, user.getDistrChannel());
			preparedStatement.setDouble(5, user.getCompCode());
			preparedStatement.setString(6, user.getOrderCreationDate());
			preparedStatement.setDouble(7, user.getOrderAmt());
			preparedStatement.setString(8, user.getOrderCurrency());
			preparedStatement.setInt(9, user.getCustomerNumber());
			preparedStatement.setDouble(10, user.getAmountUSD());
			
			preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	 @Override
	public List<Pojo> selectAllUsers() {
	
		List<Pojo> users = new ArrayList<>();
		try(Connection connect = DbConnection.connect();
				
			PreparedStatement preparedStatement = connect.prepareStatement(SELECT_ALL_USERS);){
		System.out.println("3");
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {

				System.out.println("4");
				int si = rs.getInt("Sl_No");
				int custOdId = rs.getInt("CUSTOMER_ORDER_ID");
				int salesorg = rs.getInt("SALES_ORG");
				String distrChannel = rs.getString("DISTRIBUTION_CHANNEL");
				int compCode = rs.getInt("COMPANY_CODE");
				String oRDER_CREATION_DATE = rs.getString("ORDER_CREATION_DATE");
				double oRDER_AMOUNT = rs.getDouble("ORDER_AMOUNT");
				String oRDER_CURRENCY = rs.getString("ORDER_CURRENCY");
				int cUSTOMER_NUMBER = rs.getInt("CUSTOMER_NUMBER");
				double aMOUNT_IN_USD = rs.getDouble("aMOUNT_IN_USD");
				System.out.println("5");
				users.add(new Pojo(si,custOdId,salesorg,distrChannel,compCode,oRDER_CREATION_DATE,oRDER_AMOUNT,
						oRDER_CURRENCY,cUSTOMER_NUMBER,aMOUNT_IN_USD));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	
		return users;
	}
	 @Override
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = DbConnection.connect();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);;
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;

	}
	 @Override
	public boolean updateUser(Pojo user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = DbConnection.connect();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			
			
			preparedStatement.setInt(1, user.getCustomerOrderId());
			preparedStatement.setInt(2, user.getSalesOrg());
			preparedStatement.setString(3, user.getDistrChannel());
			preparedStatement.setDouble(4, user.getCompCode());
			preparedStatement.setString(5,  user.getOrderCreationDate());
			preparedStatement.setDouble(6, user.getOrderAmt());
			preparedStatement.setString(7, user.getOrderCurrency());
			preparedStatement.setInt(8, user.getCustomerNumber());
			preparedStatement.setDouble(9, user.getAmountUSD());
			preparedStatement.setInt(10, user.getSI_No());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
		
	}
		
}
