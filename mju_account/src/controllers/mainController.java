package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import popupcontrollers.popup;
import popupcontrollers.accountPopupController;


import database.user_info;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lib.CalendarInfo;

public class mainController implements Initializable 
{
	@FXML Label name;
	@FXML Label year_cal;//달력위에 표시되는 날짜
	@FXML Label month_cal;//달력위에 표시되는 닐짜
	@FXML Label year_cur;//현재의 연
	@FXML Label month_cur;//현재의 월
	@FXML Label date_cur;//현재의 일
	@FXML Button accountButton;
	@FXML Button prev;
	@FXML Button next;
	@FXML GridPane CalendarGrid;//달력의 틀
	private popup accountPopup;//가계부 기록 팝업
	private Calendar calendar;//현재 날짜를 불러오기위한 용도.
	private user_info ui = null; //사용자의 정보를 가저오는 객체
	private List<calendarController> day;//달력 속에 들어가는 날짜객체의 컨트롤러
	private CalendarInfo Calin;//달력 역법 계산을 해주는 클래스
	private int year,month,date;//현재의 연월일을 정수형태로 저장해놓는다.
	private int _year,_month;//계산에 쓰기위한 수
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		ui = new user_info();//user_info 객체
		Calin = new CalendarInfo();
		day = new ArrayList<>();//컨트롤러 리스트생성
		System.out.println(ui.getId()+"번 사용자 접속");//디버그메세지 *무시*
		name.setText(ui.getName());//이름받아오기
		calendar = Calendar.getInstance();//달력객체의 인스턴스를 받아옴
		accountButton.setOnAction(event->account());
		prev.setOnAction(event->decrease_date());
		next.setOnAction(event->increase_date());
		year = calendar.get(Calendar.YEAR);//연
		month = calendar.get(Calendar.MONTH)+1;//월
		date = calendar.get(Calendar.DATE);//일
		this._year = this.year;//값을 복사한다.
		this._month = this.month;//값을 복사한다.
		year_cal.setText(Integer.toString(this.year));
		month_cal.setText(Integer.toString(this.month));
		year_cur.setText(Integer.toString(this.year));
		month_cur.setText(Integer.toString(this.month));
		date_cur.setText(Integer.toString(this.date));
		System.out.println("현재 날짜:"+year+"년 "+month+"월");
		calendarinit(this.year,this.month);
		calendar_date_init(this.year, this.month);
	}
	public void init_date(int year,int month)//달력 위에뜨는 날짜를 보여준다.
	{
		year_cal.setText(Integer.toString(year));//다시 설정해준다.
		month_cal.setText(Integer.toString(month));//다시 설정해준다.
	}
	public void increase_date()
	{
		if(_month>=12){
			_month=1;
			_year++;
		}
		else
		{
			_month++;
		}
		init_date(_year,_month);//달력위에 표시되어지는 연월 날짜를 씀
		calendarinit(_year,_month);//달력을 그림
		calendar_date_init(_year,_month);//달력에 날짜를 새김
	}
	public void decrease_date()
	{
		if(_month<=1){
			_month=12;
			_year--;
		}
		else
		{
			_month--;
		}
		init_date(_year,_month);//달력위에 표시되어지는 연월 날짜를 씀 works well
		calendarinit(_year,_month);//달력을 그림 works well
		calendar_date_init(_year,_month);//달력에 날짜를 새김
	}
	/*그리드 안에있는 children을 remove한다. 8번째 요소부터 그리드가 가지고있는 요소의 갯수만큼 지운다.
	 8번째 요소부터 시작하는 이유는, 1~7번째 요소는 요일을 기록하는 label이 있기 때문이다.*/
	public void remove()
	{
		CalendarGrid.getChildren().remove(8,CalendarGrid.getChildren().size());
		day.removeAll(day);
	}
	/*달력을 그린다. 정확히는 달력그리드에 year,month에 맞게 달력의 날짜를 배치하는 역할을한다.*/
	public void calendarinit(int year,int month)
	{
		int firstday = Calin.firstdate(year, month)%7;//월의 첫번째 요일 ex) Calin.firstdate(2020,11) = 0(일) 이다.
		int lastday = Calin.leap_date(year, month);//해당달의 마지막날 ex) Calin.leap_date(2020,2) = 29 이다
		remove();
		for(int i=1;i<7;i++)
		{
			for(int j=0;j<7;j++)
			{
				FXMLLoader loader = new FXMLLoader();//달력 xml파일을 불러옴
				loader.setLocation(getClass().getResource("/view/Calendar.fxml"));//달력의일을 불러옴
				 try 
				 {
				    if(i==1 && j<firstday)//해당 달의 1일부터 달력을 그린다.
				    	continue;
				    else if((((i-1)*7) - (firstday)+j) == lastday)//해당달의 끝까지 그리게되면 달력을 그만그림.
				    	return;
				    else//어느조건에도 걸리지않으면 달력을 출력한다.
				    {
						    AnchorPane days = loader.load();
							CalendarGrid.add(days,j,i);//각행과 열에 day를 넣는다.
							calendarController dc = loader.getController();
							day.add(dc);
				    	}
				   }
				 catch (IOException e) 
				 {
						e.printStackTrace();
						System.out.printf("%d행%d열 그리는데 문제 발생 \n",i,j);
				 }
			}
		}
	}
	public void calendar_date_init(int year,int month)
	{
		System.out.println("함수 호출됨");
		int i = 1;//인덱스 넘버
		int j = 0;
		int lastday = Calin.leap_date(year, month);//마지막날  ex. (2019,2) -> 28
		System.out.println("lastday: "+lastday);
		for (calendarController date : day) {
			System.out.println(j);
			j++;
		}
		for(calendarController date : day)//날짜 객체 컨트롤러 리스트 끝까지
		{
			if(i>lastday)
				break;
			date.setDate_Cal(i);//인덱스넘버 i는 1씩늘어나기때문에 해당 달의 마지막날 까지 일(日)을 써넣을수있다.
			date.setdate(year, month);//날짜객체는 일(日)뿐만 아니라 월일 도 필요하다. 달력에 직접 표시하지는 않지만 멤버로 가지고 있어야한다.
			i++;
		}
		System.out.println(i);
	}
	public void account() 
	{
		System.out.println("account");
		accountPopup = new popup("account");
		accountPopup.setLocation("/view/accountPopup.fxml");
		accountPopup.show();
	}
	
}