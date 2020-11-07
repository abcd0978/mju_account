package lib;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class fxmlHandler {
	FXMLLoader loader;
	Parent root;
	Scene scene;
	
	public fxmlHandler() {
		loader = new FXMLLoader();
	}
	
	public fxmlHandler(String location) {
		loader = new FXMLLoader(getClass().getResource(location));
		try {	
			root = (Parent) loader.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Init failed.");
			loader = null;
		}
	}
	
	public void setLocation(String location) {
		if(loader == null) {
			loader = new FXMLLoader(getClass().getResource(location));
		}else {
			loader.setLocation(getClass().getResource(location));
		}
		try {	
			root = (Parent) loader.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Init failed.");
			loader = null;
		}
	}
	
	public Scene getScene() {
		return new Scene(root);
	}
	
	public Initializable getController() {
		return loader.getController();
	}
}