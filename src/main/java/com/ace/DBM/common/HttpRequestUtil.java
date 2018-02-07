package com.ace.DBM.common;

import java.io.IOException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.ace.DBM.common.PropertiesUtil;

public class HttpRequestUtil {

	public static StringBuffer getHttpRequest(String urlPath) throws IOException {
		StringBuffer responseInfo = new StringBuffer();

		String Neo4j_ip = PropertiesUtil.GetValueByKey("Neo4j_ip");
		String Neo4j_port = PropertiesUtil.GetValueByKey("Neo4j_port");
		String Neo4j_user = PropertiesUtil.GetValueByKey("Neo4j_user");
		String Neo4j_password = PropertiesUtil.GetValueByKey("Neo4j_password");
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(Neo4j_user, Neo4j_password));
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		try {
			HttpGet httpget = new HttpGet("http://" + Neo4j_ip + ":" + Neo4j_port + urlPath);
			

			CloseableHttpResponse response = httpclient.execute(httpget);
			try {

				responseInfo.append(EntityUtils.toString(response.getEntity()));

			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.close();
		}

		return responseInfo;
	}
}
