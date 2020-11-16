package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.Account;
import database.user_account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class calendarController 
{
	@FXML Label date_Cal;
	@FXML Label income;
	@FXML Label expend;
	private int year,month,date;
	private user_account ua;
	public void setDate_Cal(int date)
	{
		date_Cal.setText(Integer.toString(date));
		ua = new user_account();
	}
	public void setinex() throws SQLException
	{
		Account[] temp = ua.get(year, month, date);
		int income=0;
		int expend=0;
		for (Account account : temp) {
			expend+=account.getExpenditure();
			income+=account.getIncome();
		}
		this.income.setText(Integer.toString(income));
		this.expend.setText(Integer.toString(expend));
	}
	//오늘을 알려주는 메소드
	public void setToday()
	{
		date_Cal.setStyle("-fx-background-color:#38ee00");
	}
	public void setdate(int year ,int month)//계산하기 위해서 날짜를 멤버변수로 가지고 있는다.
	{
		this.year = year;
		this.month = month;
		this.date = Integer.parseInt(date_Cal.getText());
		try {
			setinex();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
