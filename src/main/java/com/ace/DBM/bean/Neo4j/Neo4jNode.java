package com.ace.DBM.bean.Neo4j;

import javafx.collections.ObservableList;

public class Neo4jNode {

	private String labelName;
	private String id;
	private ObservableList<Neo4jNodeProperties> propertiesList;

	public Neo4jNode(String labelName, String id, ObservableList<Neo4jNodeProperties> propertiesList) {
		this.labelName = labelName;
		this.id = id;
		this.propertiesList = propertiesList;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ObservableList<Neo4jNodeProperties> getPropertiesList() {
		return propertiesList;
	}

	public void setPropertiesList(ObservableList<Neo4jNodeProperties> propertiesList) {
		this.propertiesList = propertiesList;
	}

}
