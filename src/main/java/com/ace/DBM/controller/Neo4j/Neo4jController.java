package com.ace.DBM.controller.Neo4j;

import java.io.IOException;

import com.ace.DBM.common.PropertiesUtil;
import com.ace.DBM.dao.Neo4j.Neo4jRest;
import com.google.gson.JsonSyntaxException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Neo4jController {
	
	@FXML
	private BorderPane main_pane;
	@FXML
	private VBox main_left_pane;
	@FXML
	private TreeView<String> main_left_tree;
	@FXML
	private MenuItem left_tree_menuitem_create;
	@FXML
	private MenuItem left_tree_menuitem_query;
	
	
	
	@FXML
	private TabPane main_center_tabpane;

	public void init() throws JsonSyntaxException, IOException {

		TreeItem<String> ti = new TreeItem<String>(PropertiesUtil.GetValueByKey("Neo4j_name"));

		Neo4jRest neo4j = new Neo4jRest();
		
		Node left_node1 = main_left_pane.getChildren().get(0);

		if (left_node1 instanceof TreeView) {
			main_left_tree = (TreeView<String>)left_node1;
		} else {
			System.out.println("主界面异常");
		}
		
		main_left_tree.setRoot(ti);

		for (String label : neo4j.getLabels()) {
			TreeItem<String> tree_item = new TreeItem<String>(label);
			main_left_tree.getRoot().getChildren().add(tree_item);
		}
	}

	@FXML
	public void mouseClick(MouseEvent event) {
		
		if (main_left_tree.getSelectionModel().getSelectedItem() == null) {
			hideAllMenuItem();
		} else {
			showAllMenuItem();
			if (event.getButton() == MouseButton.SECONDARY) {
				System.out.println("true");
			}
		}

	}

	private void showAllMenuItem() {
		for (MenuItem item_temp : main_left_tree.getContextMenu().getItems()) {
			item_temp.setVisible(true);
		}
	}

	private void hideAllMenuItem() {
		for (MenuItem item_temp : main_left_tree.getContextMenu().getItems()) {
			item_temp.setVisible(false);
		}
	}
	
	public void addNode()
	{
		Tab tab_add = new Tab();
		Parent tab_root;
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/Neo4j/MainAddNode.fxml")); 
			tab_root = fxmlLoader.load();
			
			
			tab_add.setContent(tab_root);
			tab_add.setText("test");
			tab_add.setClosable(true);
			
			Neo4jAddNodeController mainController = fxmlLoader.getController();
			mainController.init();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		main_center_tabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
		main_center_tabpane.getTabs().add(tab_add);
		main_center_tabpane.getSelectionModel().selectLast();
		
	}
	
	public void queryNode()
	{
		Tab tab_add = new Tab();
		Parent tab_root;
		
		try {			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/Neo4j/MainQueryNode.fxml")); 
			tab_root = fxmlLoader.load();
			
			
			tab_add.setContent(tab_root);
			tab_add.setText("test");
			tab_add.setClosable(true);
			
			Neo4jQueryNodeController mainController = fxmlLoader.getController();
			mainController.init(main_left_tree.getSelectionModel().getSelectedItem().getValue());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		main_center_tabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
		main_center_tabpane.getTabs().add(tab_add);
		main_center_tabpane.getSelectionModel().selectLast();
		
	}
}
