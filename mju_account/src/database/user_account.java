package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user_account {
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private user_info ui;
	/*id,income or expenditure,name,category,date,accountID*/
	public user_account()
	{
		con = DBConnection.getInstance();
		ui = new user_info();
	}
	//수입
	public void setIncome(int income,String name,String category, int year, int month, int date) throws SQLException 
	{
		String query = "INSERT INTO account (id,income,name,category,date) VALUE(?,?,?,?,?)";
		String current = year+"-"+month+"-"+date;//파라매터로 온 날짜 스트링으로 저장하기.
		ps = con.prepareStatement(query);
		ps.setInt(1, ui.getId());//사용자 아이디
		ps.setInt(2, income);//가격
		ps.setString(3,name);//이름
		ps.setString(4,category);//카테고리
		ps.setString(5,current);//날짜
		System.out.println(ps.toString());
		ps.execute();
	}
	public int[] getIncome(int income, int year, int month, int date) throws SQLException 
	{
		String current = year+"-"+month+"-"+date;//파라매터로 온 날짜 스트링으로 저장하기.
		String query = "SELECT income account where date = ?";
		
		int[] result = {0,};
		return result;
	}
	//소비
	public void setExpenditure(int expenditure,String name,String category,int year, int month, int date) throws SQLException 
	{
		String query = "INSERT INTO account (id,expenditure,name,category,date) VALUE(?,?,?,?,?)";
		String current = year+"-"+month+"-"+date;//파라매터로 온 날짜 스트링으로 저장하기.
		ps = con.prepareStatement(query);
		ps.setInt(1, ui.getId());//사용자 아이디
		ps.setInt(2, expenditure);//가격
		ps.setString(3,name);//이름
		ps.setString(4,category);//카테고리
		ps.setString(5,current);//날짜
		System.out.println(ps.toString());
		ps.execute();
	}
	public int[] getExpenditure(int expend, int year, int month, int date) throws SQLException 
	{
		String current = year+"-"+month+"-"+date;//파라매터로 온 날짜 스트링으로 저장하기.
		int[] result = {0,};
		return result;
	}
}