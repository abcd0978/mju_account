package database;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DBConnection 
{
	private final static String JDCB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String DB_URL = "jdbc:mysql://14.38.252.76/account?characterEncoding=UTF-8&serverTimezone=UTC";//mysql�� �ּ�
	private final static String USER_NAME = "kohd";//����� ���̵� ���
	private final static String PASSWORD = "0001";
	private static Connection con = null;
	private DBConnection() {}//���ٸ��ϰ� ���Ƴ���
	public static Connection getInstance()
	{
		if(con==null)
		{
			try 
			{
				Class.forName(JDCB_DRIVER);//critical 
				con = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
				System.out.println("///////DB객체 생성됨//////");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("�������");
			}
		}
		return con;
	}
	
}
