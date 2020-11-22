package database;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DBConnection 
{
	private final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String DB_URL = "jdbc:mysql://34.64.123.148:3306/account?characterEncoding=UTF-8&serverTimezone=UTC";//mysql占쏙옙 占쌍쇽옙
	private final static String USER_NAME = "kmg";
	private final static String PASSWORD = "0001";
	private static Connection con = null;
	protected static Statement st = null;
	protected static ResultSet rs;
	private DBConnection() {}
	public static Connection getInstance()
	{
		if(con==null)
		{
			try 
			{
				Class.forName(JDBC_DRIVER);//critical 
				con = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
				st = con.createStatement();
				System.out.println("///////////////DB Connected/////////////");
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("DB Connect Error");
			}
		}
		return con;
	}
	
}