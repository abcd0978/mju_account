package popupcontrollers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.Account;
import database.user_account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lib.BinarySearch;
import lib.QuickSort;
import lib.linearSearch;

public class detailPopupController implements Initializable
{
	
	@FXML private Label date_Label;
	@FXML private Label year_Label;
	@FXML private Label month_Label;
	@FXML private TableView<Account> table;
	@FXML private TableColumn<Account, String> Name;
	@FXML private TableColumn<Account, Integer> income;
	@FXML private TableColumn<Account, Integer> expend;
	@FXML private Button order_by_price;
	@FXML private Button order_by_alpha;
	@FXML private Button search;
	@FXML private TextField textfield;
	private int _year,_month,_date;
	private user_account ua;
	private Account[] accounts;
	private ObservableList<Account> accts;
	private BinarySearch bs;
	private linearSearch ls;
	private QuickSort qs;
	//이름은 setDate이지만 사실상 여기서 대부분의 초기화가 이루어진다.
	public void setDate(int year,int month,int date)
	{
		year_Label.setText(Integer.toString(year));
		month_Label.setText(Integer.toString(month));
		date_Label.setText(Integer.toString(date));
		this._year = year;
		this._month = month;
		this._date = date;
		ua = new user_account();
		try {
			accounts = ua.get(this._year,this._month,this._date);
			accts = FXCollections.observableArrayList(accounts);
			table.setItems(accts);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		bs = new BinarySearch();
		ls = new linearSearch();
		qs = new QuickSort();
		order_by_price.setOnAction(event->orderByPrice());
		order_by_alpha.setOnAction(event->orderByAlpha());
		search.setOnAction(event->searchAct());
		Name.setCellValueFactory(new PropertyValueFactory<>("name"));
		income.setCellValueFactory(new PropertyValueFactory<>("income"));
		expend.setCellValueFactory(new PropertyValueFactory<>("expenditure"));
	}
	public void searchAct() 
	{
		String text = textfield.getText();
		char check;
		boolean isnum = true;
		//숫자를 입력하면 숫자로 검색하고, 문자를 입력하면 문자로 검색한다.
		for(int i=0;i<text.length();i++)
		{
			check = text.charAt(i);
			if( check < 48 || check > 58)
			{
				//숫자가 아니면,
				isnum = false;
				break;
			}
		}
		if(isnum)
		{
			accts = FXCollections.observableArrayList(bs.binarySearch(Integer.parseInt(text),accounts));
			table.setItems(accts);
		}
		else
		{
			accts = FXCollections.observableArrayList(ls.linearSearch(text, accounts));
			table.setItems(accts);
		}
	}
	public void orderByAlpha()//구현 완료
	{
		accounts = qs.quickSort_S(accounts, 0, accounts.length-1);
		accts = FXCollections.observableArrayList(accounts);
		table.setItems(accts);
	}
	public void orderByPrice()//구현완료
	{
		accounts = qs.quickSort(accounts, 0, accounts.length-1);
		accts = FXCollections.observableArrayList(accounts);
		table.setItems(accts);
	}
	
}
