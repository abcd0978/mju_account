package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

public class user_info 
{
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private static String name = null;
	private static int id;
	public user_info()
	{
		con = DBConnection.getInstance();
	}
	public void register(String name, String id, String pass, String account) throws SQLException
	{
		String query = "INSERT INTO members(name, loginid, password, account) VALUES(?, ?, ?, ?)";
		ps = con.prepareStatement(query);
		ps.setString(1, name);//이름
		ps.setString(2, id);//id
		ps.setString(3, pass);//패스워드
		ps.setString(4, account);//잔고
		ps.execute();//쿼리실행
		System.out.println("회원가입되었음");
	}
	public boolean login(String id, String pass) throws SQLException
	{
		String query = "SELECT * FROM members WHERE loginid = ? AND password = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, id);//아이디
		ps.setString(2, pass);//패스워드
		if(ps.execute())//쿼리실행
			rs = ps.getResultSet();
		while(rs.next())
		{
			String _id = rs.getString("loginid");
			String _pass = rs.getString("password");
			if(_id.equals(id) && _pass.equals(pass))//아이디와 패스워드가 같을시에
			{
				this.id = rs.getInt("id");//사용자의 고유아이디저장
				this.name = rs.getString("name");//사용자의 이름 저장
				return true;//true를 리턴하고 함수종료
			}
		}
		return false;//while문을 다돌았는데 못찾았으면 false임.
	}
	public int getId()
	{
		return this.id;
	}
	public String getName()
	{
		return this.name;
	}
}
