package com.ace.DBM.controller.Neo4j;

import java.util.Map;

import com.ace.DBM.bean.Neo4j.Neo4jNode;
import com.ace.DBM.bean.Neo4j.Neo4jNodeProperties;
import com.ace.DBM.dao.Neo4j.Neo4jJDBC;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Neo4jQueryNodeController {

	@FXML
	private Label LabelName;
	@FXML
	private TableView<Neo4jNodeProperties> main_left_querynode_properties;
	@FXML
	private TreeView<String> main_left_querynode_node;
	@FXML
	private TableColumn<Neo4jNodeProperties, String> main_left_querynode_properties_name;
	@FXML
	private TableColumn<Neo4jNodeProperties, String> main_left_querynode_properties_value;
	
	Map<String, Neo4jNode> rs = null;

	public void init(String labelName) {
		Neo4jJDBC neo4j = new Neo4jJDBC();
		
		rs = neo4j.queryNode(labelName);
		
		main_left_querynode_node.setRoot(new TreeItem<String>("root"));
		main_left_querynode_node.setShowRoot(false);
		
		for(String nodeId : rs.keySet())
		{
			main_left_querynode_node.getRoot().getChildren().add(new TreeItem<String>(nodeId));
		}
		
		neo4j.closeConn();
		
	}
	
	@FXML
	public void queryNode()
	{		
		Neo4jNode node = rs.get(main_left_querynode_node.getSelectionModel().getSelectedItem().getValue());
		
		LabelName.setText(node.getLabelName());
				
		main_left_querynode_properties_name.setCellValueFactory(new PropertyValueFactory<>("key"));

		main_left_querynode_properties_value.setCellValueFactory(new PropertyValueFactory<>("value"));

		main_left_querynode_properties.setItems(node.getPropertiesList());
	}

}
