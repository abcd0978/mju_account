package popupcontrollers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

import database.user_account;
import database.user_info;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class fixedincomePopupController implements Initializable 
{
	@FXML ChoiceBox income_date;
	@FXML ChoiceBox income_category;
	@FXML TextField income_money;
	@FXML TextField income_title;
	@FXML Button income_Button;
	@FXML TextField how_long_income;
	@FXML TextField start_month_income;
	@FXML TextField start_date_income;
	@FXML CheckBox today_check_income;
	@FXML Label saved_la_in;
	
	@FXML ChoiceBox spend_date;
	@FXML ChoiceBox spend_category;
	@FXML TextField spend_money;
	@FXML TextField spend_title;
	@FXML Button spend_Button;
	@FXML TextField how_long_spend;
	@FXML TextField start_month_spend;
	@FXML TextField start_date_spend;
	@FXML CheckBox today_check_spend;
	@FXML Label saved_la_ex;
	
	
	private int year,month,date;
	private user_account ua;
	private Calendar calendar;
	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources)
	{
		income_date.setItems(FXCollections.observableArrayList("일주일", "한달","일년"));
		income_date.setValue("일주일");
		income_category.setItems(FXCollections.observableArrayList("용돈","일당","알바","월급","기타"));
		income_category.setValue("용돈");
		spend_date.setItems(FXCollections.observableArrayList("일주일", "한달","일년"));
		spend_date.setValue("일주일");
		spend_date.setValue("하루");
		spend_category.setItems(FXCollections.observableArrayList("식비","교통","문화생활","마트/편의점","교육","페션/미용","유흥","기타"));
		spend_category.setValue("식비");
		today_check_income.setOnAction(event->check1());
		today_check_spend.setOnAction(event->check2());
		income_Button.setOnAction(event->{
			try {
				income_ButtonPressed();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		spend_Button.setOnAction(event->{
			try {
				spend_ButtonPressed();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		ua = new user_account();
		calendar = Calendar.getInstance();
		this.year = calendar.get(Calendar.YEAR);//연
		this.month = calendar.get(Calendar.MONTH)+1;//월
		this.date = calendar.get(Calendar.DATE);//일
	}
	private void check1() 
	{
		start_month_income.setText(Integer.toString(this.month));
		start_date_income.setText(Integer.toString(this.date));
	}
	public void check2()
	{
		start_date_spend.setText(Integer.toString(this.date));
		start_month_spend.setText(Integer.toString(this.month));
	}
	//고정수입 기록
	public void income_ButtonPressed() throws SQLException 
	{
		int dateIndex = income_date.getSelectionModel().getSelectedIndex();//1은 일주일,2는 한달
		String category = income_category.getSelectionModel().getSelectedItem().toString();//카테고리 스트링으로 저장
		int money = Integer.parseInt(income_money.getText());//수입 정숭형태로 저장
		String title = income_title.getText();//내용 저장
		int  how_long = Integer.parseInt(this.how_long_income.getText());
		ua.setFixedIncome(money, title, category,this.year,Integer.parseInt(start_month_income.getText()),Integer.parseInt(start_date_income.getText()),dateIndex,how_long);
		//매개변수로 수입,이름,카테고리,연웡일,간격선택,기간선택이 들어간다.
		saved_la_in.setText("저장됨");
	}
	//고정 지출 기록
	public void spend_ButtonPressed() throws SQLException 
	{
		int dateIndex = spend_date.getSelectionModel().getSelectedIndex();
		String category = spend_category.getSelectionModel().getSelectedItem().toString();
		int money = Integer.parseInt(spend_money.getText());
		String title = spend_title.getText();
		int how_long = Integer.parseInt(how_long_spend.getText());
		ua.setFixedExpenditure(money, title, category,this.year,Integer.parseInt(start_month_spend.getText()),Integer.parseInt(start_date_spend.getText()) ,dateIndex,how_long);
		//매개변수로 수입,이름,카테고리,연웡일,간격선택,기간선택이 들어간다.
		saved_la_ex.setText("저장됨");
	}
}