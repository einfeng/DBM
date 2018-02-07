package com.ace.DBM.bean.Neo4j;

import javafx.beans.property.SimpleStringProperty;

public class Neo4jNodeProperties {

	private final SimpleStringProperty key;
	private final SimpleStringProperty value;
	
	public Neo4jNodeProperties(String key, String value) {
		this.key = new SimpleStringProperty(key);
		this.value = new SimpleStringProperty(value);
	}
	
	public String getKey() {
		return key.get();
	}
	public void setKey(String t_key) {
		key.set(t_key);;
	}
	public String getValue() {
		return value.get();
	}
	public void setValue(String t_value) {
		value.set(t_value);;
	}
}
