package com.ace.DBM.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ace.DBM.common.JdbcUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class LogonController {

	@FXML
	private ChoiceBox<String> logon_db_type;

	public void init() {
		
		ObservableList<String> list = FXCollections.observableArrayList();
		
		JdbcUtil jdbc = new JdbcUtil("Derby");
		
		ResultSet rs = jdbc.executeQuerySql("select * from tab_db_dbtype");
		
		try {
			while(rs.next())
			{
				list.add((rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		logon_db_type.setItems(FXCollections.observableArrayList(list));
		logon_db_type.getSelectionModel().selectFirst();

		logon_db_type.getSelectionModel().selectedIndexProperty().addListener((ov, oldv, newv) -> {
			System.out.println(logon_db_type.getSelectionModel().getSelectedItem());
		});

	}

	// @FXML
	public void choiceDB() {
		System.out.println(logon_db_type.getSelectionModel().getSelectedItem());
	}

}
