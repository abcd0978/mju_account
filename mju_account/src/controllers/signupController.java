package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.user_info;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class signupController implements Initializable
{
	 
	@FXML Button signup;
	@FXML Button goback;
	@FXML Button dup;
	@FXML PasswordField pass;
	@FXML PasswordField pass_con;
	@FXML TextField id;
	@FXML TextField account;
	@FXML TextField name;
	private user_info ui;
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		ui = new user_info();
		signup.setOnAction(event->register());	
	}
	public void register()
	{
		try {
			System.out.println(name.getText());
			System.out.println(id.getText());
			System.out.println(pass.getText());
			System.out.println(account.getText());
			ui.register(name.getText(), id.getText(), pass.getText(), account.getText());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}
