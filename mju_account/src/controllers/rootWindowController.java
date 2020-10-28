package controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.user_info;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class rootWindowController implements Initializable
{
	@FXML PasswordField user_pw;
	@FXML TextField user_id;
	@FXML Button login;
	@FXML Button register;
	private user_info ui = null;
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		ui = new user_info();
		login.setOnAction(event->login());
		register.setOnAction(event->signup());
	}
	public void login()
	{
		try {
			if(ui.login(user_id.getText(), user_pw.getText()))
			{
				System.out.println("logined");
				Parent login_ = FXMLLoader.load(getClass().getResource("/view/main.fxml"));// 불러오기
			    Scene scene = new Scene(login_);
			    Stage primaryStage = (Stage)login.getScene().getWindow(); // 현재 윈도우 가져오기
			    primaryStage.setScene(scene);
			}
			else
				System.out.println("login failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("로그인 오류");
		}
	}
	public void signup()
	{
		try
		{
		    Parent login = FXMLLoader.load(getClass().getResource("/view/signup.fxml"));//회원가입창 불러오기
		    Scene scene = new Scene(login);
		    Stage primaryStage = (Stage)register.getScene().getWindow(); // 현재 윈도우 가져오기
		    primaryStage.setScene(scene);
		 } 
		catch(Exception e1)
		{
		       e1.printStackTrace();
		       System.out.println("FXML불러오기 오류");
		}
	}

}
