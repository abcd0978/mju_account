package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user_account {
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private static String name = null;
	private static int id;
	
	public user_account()
	{
		con = DBConnection.getInstance();
	}
	
	public void setIncome(int income, int year, int month, int day) throws SQLException {
		//³»¿ë
	}
	
	public int getIncome(int income, int year, int month, int day) throws SQLException {
		return 1;
	}
	
	public void setExpenditure(int expenditure, int year, int month, int day) throws SQLException {
		//³»¿ë
	}
	
	public int getExpenditure(int expenditure, int year, int month, int day) throws SQLException {
		return 1;
	}
	
}