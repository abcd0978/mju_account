package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import database.user_info;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class mainController implements Initializable 
{
	@FXML Label name;
	private user_info ui = null; 
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		ui = new user_info();
		System.out.println(ui.getId());
		name.setText(ui.getName());
	}
	
}
