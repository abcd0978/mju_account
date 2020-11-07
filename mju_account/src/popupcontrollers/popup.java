package popupcontrollers;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import lib.fxmlHandler;

public class popup {
	protected fxmlHandler handler;
	protected Initializable controller;
	protected Stage window;
	public popup(String title) 
	{
		window = new Stage();
		window.setTitle(title);
		window.setResizable(false);
	}
	public popup(String title, boolean resizeable) 
	{
		window = new Stage();
		window.setTitle(title);
		window.setResizable(resizeable);
	}
	public void setLocation(String location) 
	{
		handler = new fxmlHandler(location);
		controller = handler.getController();
		//((closable)controller).setStage(window);
		window.setScene(handler.getScene());
	}
	public void setSize(int width,int height)
	{
		window.setWidth(width);
		window.setHeight(height);
	}
	public Initializable getController() 
	{
		return this.controller;
	}
	
	public void show() {
		window.show();
	}
}