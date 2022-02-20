package testcase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.Test;

import com.google.gson.Gson;

import Utility.Constants;
import Utility.Utils;
import api.ApiCall;
import api.ApiPaths;
import api.ApiTestUtils;
import io.restassured.response.Response;
import model.Login;

public class EmailLogin {
	Login login;

	@Test
	public Response TestEmailLogin() throws IOException {
//		Utils.CreatingPropertiesFile();

		login = new Login();
		Properties prop = Utils.readPropertiesFile(Constants.PropertiesFilePath);
	//	System.out.println("email "+ prop.getProperty("email"));
		login.setEmail(prop.getProperty("email"));
		login.setPassword(prop.getProperty("password"));
		Map<String,Object> bodyParams=new HashMap<String,Object>();
		bodyParams.put("email", login.getEmail());
		bodyParams.put("password",login.getPassword());

		String payload=new Gson().toJson(bodyParams);
		System.out.println("****{POST}*****");

		Response response=ApiCall.postApiMethod(payload,ApiPaths.email_login);

		ApiTestUtils.getAllApiResponse(response);
		ApiTestUtils.checkStatusCode(response, 200);
		ApiTestUtils.getStatusLineAssertTrue(response, "OK");

		String res =response.getBody().asString();
		ApiTestUtils.checkResponse(response, "Success");
		return response;
		

	//	Utils.logPrint("json data "+ApiTestUtils.getJsonPath(response));

	}

}
