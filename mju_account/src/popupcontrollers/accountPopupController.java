package popupcontrollers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

import database.Account;
import database.user_account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class accountPopupController implements Initializable 
{
	@FXML TextField name;
	@FXML TextField price;
	@FXML ListView<String> category;
	@FXML Button save;
	@FXML ToggleGroup select;
	@FXML RadioButton income_ra;
	@FXML RadioButton expend_ra; 
	@FXML Label category_la;
	@FXML Label name_la;
	@FXML Label price_la;
	@FXML Label date_la;
	@FXML Label saved_la;
	private ObservableList<String> listItems;
	private user_account ua;
	private int year,month,date;
	private Calendar cal;
	public void initialize(URL location, ResourceBundle resources) 
	{
		listItems = FXCollections.observableArrayList("식비","교통","문화생활","마트/편의점","교육","페션/미용","기타","일당","용돈");
		category.setItems(listItems);
		cal = Calendar.getInstance();
		ua = new user_account();
		save.setOnAction(event->save());
		income_ra.setToggleGroup(select);
		expend_ra.setToggleGroup(select);
		//현재 날짜 받아오기
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH)+1;
		date = cal.get(Calendar.DATE);
	}
	public void save()
	{
		if(select.getSelectedToggle()==null)
		{
			System.out.println("라디오버튼 안눌림");
			return;
		}
		RadioButton temp = (RadioButton)select.getSelectedToggle();
		int tog = Integer.parseInt(temp.getId());
		String selected = category.getSelectionModel().getSelectedItem();
		try {
			if(tog==0)
				ua.setExpenditure(Integer.parseInt(price.getText()),name.getText(),selected, year, month, date);
			else if(tog==1)
				ua.setIncome(Integer.parseInt(price.getText()), name.getText(),selected, year, month, date);
			
			category_la.setText(selected);
			name_la.setText(name.getText());
			date_la.setText(year+"-"+month+"-"+date);
			price_la.setText(price.getText());
			saved_la.setText("저장됨");
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
}