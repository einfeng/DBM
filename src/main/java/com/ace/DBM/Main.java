package com.ace.DBM;
	

import com.ace.DBM.controller.LogonController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui/Logon.fxml")); 
			Parent root = fxmlLoader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("css/Main.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("登录");
			
			LogonController mainController = fxmlLoader.getController();
			mainController.init();
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
