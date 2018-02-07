package com.ace.DBM.controller.Neo4j;

import com.ace.DBM.bean.Neo4j.Neo4jNode;
import com.ace.DBM.bean.Neo4j.Neo4jNodeProperties;
import com.ace.DBM.dao.Neo4j.Neo4jJDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class Neo4jAddNodeController {

	@FXML
	private TextField main_left_addnode_label_name;
	@FXML
	private TableView<Neo4jNodeProperties> main_left_addnode_properties;
	@FXML
	private TableColumn<Neo4jNodeProperties, String> main_left_addnode_properties_name;
	@FXML
	private TableColumn<Neo4jNodeProperties, String> main_left_addnode_properties_value;
	@FXML
	private Button main_left_addnode_add_properties;
	@FXML
	private Button main_left_addnode_add_save;

	private ObservableList<Neo4jNodeProperties> Neo4jNodePropertiesData;

	public void init() {
		Neo4jNodePropertiesData = FXCollections.observableArrayList();

		main_left_addnode_properties_name.setCellValueFactory(new PropertyValueFactory<>("key"));
		main_left_addnode_properties_name.setCellFactory(TextFieldTableCell.<Neo4jNodeProperties>forTableColumn());

		main_left_addnode_properties_value.setCellValueFactory(new PropertyValueFactory<>("value"));
		main_left_addnode_properties_value.setCellFactory(TextFieldTableCell.<Neo4jNodeProperties>forTableColumn());

		main_left_addnode_properties.setItems(Neo4jNodePropertiesData);
	}

	@FXML
	public void addProperties() {
		Neo4jNodePropertiesData.add(new Neo4jNodeProperties("NULL", "NULL"));
	}

	@FXML
	public void saveKey(CellEditEvent<Neo4jNodeProperties, String> event) {
		((Neo4jNodeProperties) event.getTableView().getItems().get(event.getTablePosition().getRow()))
				.setKey(event.getNewValue());
	}

	@FXML
	public void saveValue(CellEditEvent<Neo4jNodeProperties, String> event) {
		((Neo4jNodeProperties) event.getTableView().getItems().get(event.getTablePosition().getRow()))
				.setValue(event.getNewValue());
	}

	@FXML
	public void saveNode() {

		Neo4jNode lb = new Neo4jNode(main_left_addnode_label_name.getText(), "",
				main_left_addnode_properties.getItems());

		Neo4jJDBC neo4j = new Neo4jJDBC();

		String status = neo4j.createNode(lb);

		neo4j.closeConn();

		String alertMessage = "";

		if (status.equals("successed")) {
			alertMessage = "创建成功";
		} else {
			alertMessage = "创建失败";
		}

		Alert alert = new Alert(Alert.AlertType.INFORMATION, alertMessage, new ButtonType("确定", ButtonData.OK_DONE));
		alert.setTitle("创建状态");
		alert.show();

	}

}
