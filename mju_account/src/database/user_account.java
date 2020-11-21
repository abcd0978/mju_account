package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

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
		ps.close();
	}
	//당일에 발생한 수입,지출 정보를 반환한다.
	public Account[] get(int year, int month, int date) throws SQLException 
	{
		String current = year+"-"+month+"-"+date;//파라매터로 온 날짜 스트링으로 저장하기.
		String query = "SELECT * FROM account WHERE date = ? AND id = ?";
		String query2 = "SELECT COUNT(*) FROM account WHERE date = ? AND id = ?";
		ps = con.prepareStatement(query2);
		ps.setString(1,current);
		ps.setInt(2, ui.getId());
		ps.execute();
		rs = ps.getResultSet();
		rs.next();
		int number = rs.getInt("COUNT(*)");
		Account[] temp = new Account[number];
		ps = con.prepareStatement(query);
		ps.setString(1, current);
		ps.setInt(2, ui.getId());
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
		ps.close();
		return temp;
	}
	//월단위로 집계한다. 오직 파이차트를 만들기위해서 만든 메소드이다.
	public ObservableList<PieChart.Data> getCategoryList(int year, int month) throws SQLException 
	{
		String current = year+"-"+month;//파라매터로 온 날짜 스트링으로 저장하기.
		String[] categories = {"식비","교통","문화생활","마트/편의점","교육","페션/미용","유흥","기타"};
		ObservableList<PieChart.Data> result = FXCollections.observableArrayList();
		String query = "SELECT sum(expenditure) FROM account WHERE date like ? AND id = ? AND category = ? ";
		String query2 = "SELECT COUNT(*) FROM account WHERE date like ? AND id = ? AND category = ? ";
		
		for(int i=0;i<categories.length;i++)//카테고리의 수만큼 반복한다.
		{
			ps = con.prepareStatement(query2);
			ps.setString(1,current+"%");
			ps.setInt(2, ui.getId());
			ps.setString(3, categories[i]);
			ps.execute();
			rs = ps.getResultSet();
			rs.next();
			int number = rs.getInt("COUNT(*)");
			if(number == 0)//아무것도 없으면 continue함.
				continue;
		
			ps = con.prepareStatement(query);
			ps.setString(1, current+"%");//날짜 설정
			ps.setInt(2, ui.getId());//아이디설정
			ps.setString(3, categories[i]);//카테고리 설정
			ps.execute();//실행
			rs = ps.getResultSet();
			rs.next();//한칸뛰기
			int money = rs.getInt("sum(expenditure)");//결과 받아오기
			result.add(new PieChart.Data(categories[i],money));//넣기
		}
		ps.close();
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
		ps.close();
	}
	public void setFixedIncome(int income,String title,String category,int year,int month,int date,int interval,int how_long) throws SQLException
	{
		String current = year+"-"+month+"-"+date;
		String query = "INSERT INTO account (id,income,name,category,date) VALUE(?,?,?,?,?)";//삽입구문
		String weekIncrease =  "SELECT DATE_ADD( ? ,INTERVAL 1 WEEK);";//날짜를 늘려서 select로 구문으로 반환
		String monthIncrease =  "SELECT DATE_ADD( ? ,INTERVAL 1 MONTH);";//날짜 늘리기
		String yearIncrease =  "SELECT DATE_ADD( ? ,INTERVAL 1 YEAR);";//날짜 늘리기
		ps = con.prepareStatement(query);//insert 구문
		ps.setInt(1, ui.getId());
		ps.setInt(2, income);
		ps.setString(3, title);
		ps.setString(4, category);
		ps.setString(5, current);
		ps.execute();//먼저 현재 날짜로 insert함
		if(interval==0)//일주일
		{
			for(int i=0;i<how_long;i++)
			{
				ps = con.prepareStatement(weekIncrease);//날짜를 늘림
				ps.setString(1,current);
				ps.execute();//current를 늘려줌
				rs = ps.getResultSet();
				rs.next();
				current = rs.getString(1);
				ps = con.prepareStatement(query);//insert 구문
				ps.setInt(1, ui.getId());
				ps.setInt(2, income);
				ps.setString(3, title);
				ps.setString(4, category);
				ps.setString(5, current);
				ps.execute();//먼저 현재 날짜로 insert함
			}
		}
		else if(interval==1)//한달
		{
			for(int i=0;i<how_long;i++)
			{
				ps = con.prepareStatement(monthIncrease);//날짜를 늘림
				ps.setString(1,current);
				ps.execute();//current를 늘려줌
				rs = ps.getResultSet();
				rs.next();
				current = rs.getString(1);
				ps = con.prepareStatement(query);//insert 구문
				ps.setInt(1, ui.getId());
				ps.setInt(2, income);
				ps.setString(3, title);
				ps.setString(4, category);
				ps.setString(5, current);
				ps.execute();//먼저 현재 날짜로 insert함
			}
		}
		else if(interval==2)//일년
		{
			for(int i=0;i<how_long;i++)
			{
				ps = con.prepareStatement(yearIncrease);//날짜를 늘림
				ps.setString(1,current);
				ps.execute();//current를 늘려줌
				rs = ps.getResultSet();
				rs.next();
				current = rs.getString(1);
				ps = con.prepareStatement(query);//insert 구문
				ps.setInt(1, ui.getId());
				ps.setInt(2, income);
				ps.setString(3, title);
				ps.setString(4, category);
				ps.setString(5, current);
				ps.execute();//먼저 현재 날짜로 insert함
			}
		}
	}
	public void setFixedExpenditure(int expend,String title,String category,int year,int month,int date,int interval,int how_long) throws SQLException
	{
		String current = year+"-"+month+"-"+date;
		String query = "INSERT INTO account (id,expenditure,name,category,date) VALUE(?,?,?,?,?)";
		String setQuery = "SET @date_temp = '"+current+"';";//현재날짜 기준으로 하여
		String weekIncrease =  "SET @date_temp = DATE_ADD(@date_temp,INTERVAL 1 WEEK);";//날짜 늘리기
		String monthIncrease =  "SET @date_temp = DATE_ADD(@date_temp,INTERVAL 1 MONTH);";//날짜 늘리기
		String yearIncrease =  "SET @date_temp = DATE_ADD(@date_temp,INTERVAL 1 YEAR);";//날짜 늘리기
		if(interval==0)//일주일
		{
			for(int i=0;i<how_long;i++)
			{
				ps = con.prepareStatement(weekIncrease);//날짜를 늘림
				ps.setString(1,current);
				ps.execute();//current를 늘려줌
				rs = ps.getResultSet();
				rs.next();
				current = rs.getString(1);
				ps = con.prepareStatement(query);//insert 구문
				ps.setInt(1, ui.getId());
				ps.setInt(2, expend);
				ps.setString(3, title);
				ps.setString(4, category);
				ps.setString(5, current);
				ps.execute();//먼저 현재 날짜로 insert함
			}
		}
		else if(interval==1)//한달
		{
			for(int i=0;i<how_long;i++)
			{
				ps = con.prepareStatement(monthIncrease);//날짜를 늘림
				ps.setString(1,current);
				ps.execute();//current를 늘려줌
				rs = ps.getResultSet();
				rs.next();
				current = rs.getString(1);
				ps = con.prepareStatement(query);//insert 구문
				ps.setInt(1, ui.getId());
				ps.setInt(2, expend);
				ps.setString(3, title);
				ps.setString(4, category);
				ps.setString(5, current);
				ps.execute();//먼저 현재 날짜로 insert함
			}
		}
		else if(interval==2)//일년
		{
			for(int i=0;i<how_long;i++)
			{
				ps = con.prepareStatement(yearIncrease);//날짜를 늘림
				ps.setString(1,current);
				ps.execute();//current를 늘려줌
				rs = ps.getResultSet();
				rs.next();
				current = rs.getString(1);
				ps = con.prepareStatement(query);//insert 구문
				ps.setInt(1, ui.getId());
				ps.setInt(2, expend);
				ps.setString(3, title);
				ps.setString(4, category);
				ps.setString(5, current);
				ps.execute();//먼저 현재 날짜로 insert함
			}
		}
	}
	public String monthS(int month)
	{
		if(month>9)
			return Integer.toString(month);
		else
			return "0"+month;
	}
	public int getMonthExpenditureSum(int month) throws SQLException
	{

		String query = "SELECT sum(expenditure) FROM account WHERE id = ? AND date like '2020-"+monthS(month)+"%'";
		ps = con.prepareStatement(query);
		ps.setInt(1, ui.getId());//사용자 아이디
		//ps.setString(2, "2021-"+ monthS + "%");//이름
		ps.execute();
		
		System.out.println(ps.toString());
		rs = ps.getResultSet();
		rs.next();
		int result = rs.getInt("sum(expenditure)");
		System.out.println(result);
		return result;
	}
}
