package database;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DBConnection extends Properties
{
	private final static String JDCB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String DB_URL = "jdbc:mysql://14.38.252.76/account?characterEncoding=UTF-8&serverTimezone=UTC";//mysql의 주소
	private final static String USER_NAME = "kohd";//고동욱님 아이디 사용
	private final static String PASSWORD = "0001";
	private static Connection con = null;
	private DBConnection() {}//접근못하게 막아놓음
	public static Connection getInstance()
	{
		if(con==null)
		{
			try 
			{
				Class.forName(JDCB_DRIVER);//critical 
				con = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
				System.out.println("///////DB연결됨//////");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("연결오류");
			}
		}
		return con;
	}
	
}
