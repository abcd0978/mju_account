package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

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
	//당일에 발생한 수입,지출 정보를 반환한다.
	public Account[] get(int year, int month, int date) throws SQLException 
	{
		String current = year+"-"+month+"-"+date;//파라매터로 온 날짜 스트링으로 저장하기.
		String query = "SELECT * FROM account WHERE date = ?";
		String query2 = "SELECT COUNT(*) FROM account WHERE date = ?";
		ps = con.prepareStatement(query2);
		ps.setString(1,current);
		System.out.println(ps.toString());
		ps.execute();
		rs = ps.getResultSet();
		rs.next();
		int number = rs.getInt("COUNT(*)");
		Account[] temp = new Account[number];
		ps = con.prepareStatement(query);
		ps.setString(1, current);
		System.out.println(ps.toString());
		ps.execute();
		rs = ps.getResultSet();
		int i=0;
		while(rs.next())
		{
			temp[i] = new Account();
			temp[i].setCategory(rs.getString("category"));
			temp[i].setExpenditure(rs.getInt("expenditure"));
			temp[i].setIncome(rs.getInt("income"));
			temp[i].setName(rs.getString("name"));
			i++;
		}
		return temp;
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
}