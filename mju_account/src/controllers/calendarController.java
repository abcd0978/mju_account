package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import database.user_account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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
	public void setinex()
	{
		int[] income_arr;
		int[] expend_arr;
		
	}
	public void setdate(int year ,int month)//��¥ ��ü ������ ��¥ ����
	{
		this.year = year;
		this.month = month;
		this.date = Integer.parseInt(date_Cal.getText());
	}
	
}
