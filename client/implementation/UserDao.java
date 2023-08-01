package com.highradius.implementation;

import java.sql.SQLException;
import java.util.List;
import com.highradius.model.Pojo;

public interface UserDao {
	
	public void insertUser(Pojo user) throws SQLException;
	
	public List<Pojo> selectAllUsers();
	
	public boolean deleteUser(int id) throws SQLException;
	
	public boolean updateUser(Pojo user) throws SQLException;
}
