package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class calendarController 
{
	@FXML Label date_Cal;
	private int year,month,date;
	public void setDate_Cal(int date)
	{
		date_Cal.setText(Integer.toString(date));
	}
	public void setdate(int year ,int month)//��¥ ��ü ������ ��¥ ����
	{
		this.year = year;
		this.month = month;
		this.date = Integer.parseInt(date_Cal.getText());
	}
	
}
