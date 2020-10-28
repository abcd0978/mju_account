package controllers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Appmain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("/view/root.fxml"));
		Scene scene = new Scene(root); // Scene °´Ã¼ »ý¼º
		primaryStage.setTitle("mju_account");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String args[]) {
		launch(args);
	}
}