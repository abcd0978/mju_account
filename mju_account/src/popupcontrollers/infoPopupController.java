package popupcontrollers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.information;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;

public class infoPopupController implements Initializable {
	@FXML Pagination pagination;
	@FXML Label title1; @FXML Label title2; @FXML Label title3;
	@FXML Label text1; @FXML Label text2; @FXML Label text3;

	private information info;
	private final ChangeListener<Number> paginationChangeListener = (observable, oldValue, newValue) -> changePage();
	
	public void initialize(URL location, ResourceBundle resources) 
	{
		info = new information();

		
		int numberOfData; // 데이터베이스에 저장된 데이터 수
		
		try {
			numberOfData = info.numberofInfoData();
			
			pagination.setPageCount((int) Math.ceil((double)numberOfData/3.0)); // 한 페이지에 3개 씩 출력
			title1.setText(info.getTitle(numberOfData));
			text1.setText(info.getText(numberOfData));
			title2.setText(info.getTitle(numberOfData-1));
			text2.setText(info.getText(numberOfData-1));
			title3.setText(info.getTitle(numberOfData-2));
			text3.setText(info.getText(numberOfData-2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pagination.currentPageIndexProperty().addListener(paginationChangeListener);
	}
	
	//pagination 의 페이지가 바뀔 떄 title1,2,3 Label 을 새롭게 setText해주는 메소드
	public void changePage() {
		text1.setText(String.format("Current Page: %d", pagination.getCurrentPageIndex()));
		int CurrentPageNum = pagination.getCurrentPageIndex()+1; // 0부터 시작
		System.out.println("Current Page : " + CurrentPageNum);
		
		info = new information();
		try { // first in last out.
			int numberOfData = info.numberofInfoData();
			int dataNum = numberOfData-(CurrentPageNum-1)*3;
			
			title1.setText(info.getTitle(dataNum));
			text1.setText(info.getText(dataNum));
			title2.setText(info.getTitle(dataNum-1));
			text2.setText(info.getText(dataNum-1));
			title3.setText(info.getTitle(dataNum-2));
			text3.setText(info.getText(dataNum-2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}