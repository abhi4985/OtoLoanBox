package testcase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.gson.Gson;

import Utility.Utils;
import api.ApiCall;
import api.ApiPaths;
import api.ApiTestUtils;
import io.restassured.response.Response;

public class LoanDashboard {
	@Test
	public void getDashboard() throws IOException {
		EmailLogin email = new EmailLogin();
		Response data = email.TestEmailLogin();
	
	Map<String,Object> bodyParams=new HashMap<String,Object>();
	bodyParams.put("email", "");
	bodyParams.put("password","");

//	String payload=new Gson().toJson(bodyParams);
//	System.out.println("****{POST}****");
//
//	Response response=ApiCall.postApiMethod(payload,ApiPaths.email_login);
//
//	ApiTestUtils.getAllApiResponse(response);
//	ApiTestUtils.checkStatusCode(response, 200);
//	ApiTestUtils.getStatusLineAssertTrue(response, "OK");
//
//	String res =response.getBody().asString();
//	ApiTestUtils.checkResponse(response, "Success");
	
//	String usernames = email.TestEmailLogin().jsonPath().getString("data.token");
	Utils.logPrint("json data "+ApiTestUtils.getValueFromResponse(data, "data.token"));

}
	}
