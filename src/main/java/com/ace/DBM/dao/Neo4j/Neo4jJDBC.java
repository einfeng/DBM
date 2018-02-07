package com.ace.DBM.dao.Neo4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ace.DBM.bean.Neo4j.Neo4jNode;
import com.ace.DBM.bean.Neo4j.Neo4jNodeProperties;
import com.ace.DBM.common.JdbcUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Neo4jJDBC {

	private JdbcUtil neo4j;

	public Neo4jJDBC() {
		neo4j = new JdbcUtil("Neo4j");
	}

	public String createNode(Neo4jNode node) {
		String labelName = node.getLabelName();
		String properties = "";

		if (!node.getPropertiesList().isEmpty()) {
			for (Neo4jNodeProperties l : node.getPropertiesList()) {
				properties = properties + l.getKey() + ":\"" + l.getValue() + "\",";
			}
			properties = "{" + properties.substring(0, properties.length() - 1) + "}";
		}

		String sql = "create (n:" + labelName + " " + properties + ")";
		int status = neo4j.executeDdlSql(sql);

		if (status == 1) {
			return "successed";
		} else {
			return "failed";
		}
	}

	public Map<String, Neo4jNode> queryNode(String label) {

		String sql = "match (n:" + label + ") return n";

		ResultSet rs = neo4j.executeQuerySql(sql);


		Map<String, Neo4jNode> nodeList = new HashMap<String, Neo4jNode>();

		try {
			while (rs.next()) {

				String id = "";
				String labelName = "";
				
				ObservableList<Neo4jNodeProperties> propertiesList = FXCollections.observableArrayList();

				JsonObject properties_obj = new JsonParser().parse(rs.getString("n")).getAsJsonObject();
				Set<Entry<String, JsonElement>> set = properties_obj.entrySet();

				for (Entry<String, JsonElement> list : set) {
					if (list.getKey().equals("id")) {
						id = list.getValue().toString();
					} else if (list.getKey().equals("labels")) {
						labelName = list.getValue().toString().replace("[\"", "").replace("\"]", "");
					} else {
						propertiesList
								.add(new Neo4jNodeProperties(list.getKey().toString(), list.getValue().toString()));
					}
				}

				nodeList.put(id, new Neo4jNode(labelName, id, propertiesList));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nodeList;
	}

	public void closeConn() {
		neo4j.closeJdbc();
	}

}
