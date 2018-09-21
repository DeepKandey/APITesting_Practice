package com.qa.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.qa.parameters.CreateUser;

public class HttpPost_CreateUser {

	@Test
	public void createUser() throws ClientProtocolException, IOException {

		// 1. Creating HTTP Client
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 2. Creating HttpPost request
		HttpPost httpPost = new HttpPost("https://reqres.in/api/users");

		// 3. Creating GSON API object
		Gson gson = new Gson();

		// 4.Creating object of POJO class
		CreateUser user = new CreateUser("Deepak", "Washing");

		// 5. Converting POJO object into JSON String - marshelling
		String jsonString = gson.toJson(user); // gson.tojson() converts your POJO to JSON

		System.out.println("JSON String-->" + jsonString);

		// 6.Creating StringEntity from JSON String
		HttpEntity payLoad = new StringEntity(jsonString);

		// 7. Setting Entity for HttpPost request
		httpPost.setEntity(payLoad);

		// 8. Setting Header
		httpPost.setHeader("Content-type", "application/json");

		// 9. Executing HTTPPOST request
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

		// 10. Reading Status Line
		System.out.println(httpResponse.getStatusLine());

		// 11. Consuming response body
		String jsonStringResponse = null;
		HttpEntity responseEntity = httpResponse.getEntity();
		if (responseEntity != null) {
			jsonStringResponse = EntityUtils.toString(responseEntity, "UTF-8");
		}

		System.out.println(jsonStringResponse);

		// Converting JSON string Response into POJO --unmarshelling
		CreateUser userinJSON = gson.fromJson(jsonStringResponse, CreateUser.class);
		System.out.println(userinJSON);
		System.out.println(userinJSON.getCreatedAt());
		System.out.println(userinJSON.getName() + " " + user.getName());
		Assert.assertEquals(userinJSON.getName(), user.getName());

	}

}