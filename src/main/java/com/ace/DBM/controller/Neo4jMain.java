package com.ace.DBM.controller;
	
import com.ace.DBM.controller.Neo4j.Neo4jController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Neo4jMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ui/Neo4j/Neo4jMain.fxml")); 
			Parent root = fxmlLoader.load();
			
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("../css/Main.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Neo4j");
			primaryStage.setMaximized(true);
			
			Neo4jController mainController = fxmlLoader.getController();
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
