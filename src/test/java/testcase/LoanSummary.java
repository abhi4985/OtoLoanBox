package testcase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.girnarsoft.api.ApiCall;
import com.girnarsoft.api.ApiPaths;
import com.girnarsoft.api.ApiTestUtils;
import com.google.gson.Gson;

import Utility.Utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoanSummary {
	
	@Test
	public void getLoanSummary() throws IOException {
		EmailLogin email = new EmailLogin();
		Response response = email.TestEmailLogin();
	
	Map<String,Object> bodyParams=new HashMap<String,Object>();
	bodyParams.put("dealer_id", ApiTestUtils.getValueFromResponse(response, "data.user_data.user_id"));
	bodyParams.put("user_id",ApiTestUtils.getValueFromResponse(response, "data.user_data.user_id"));

	String payload=new Gson().toJson(bodyParams);

	String Token = ApiTestUtils.getValueFromResponse(response, "data.token");
	Response resp=ApiCall.postApiWithHeader(payload,ApiPaths.loan_summary,Token);

	ApiTestUtils.getAllApiResponse(resp);
	ApiTestUtils.checkStatusCode(resp, 200);
//	ApiTestUtils.getStatusLineAssertTrue(response, "OK");
//
//	String res =response.getBody().asString();
//	ApiTestUtils.checkResponse(response, "Success");
	
	
	Utils.logPrint("json data "+Token);

}

}
