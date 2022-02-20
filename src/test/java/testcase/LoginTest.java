package testcase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import Utility.Constants;
import Utility.Utils;
import api.ApiCall;
import api.ApiPaths;
import api.ApiTestUtils;
import io.restassured.response.Response;
import model.Login;

public class LoginTest {

	Login login;

	@Test
	public void TestLogin() throws IOException {
//		Utils.CreatingPropertiesFile();

		login = new Login();
		Properties prop = Utils.readPropertiesFile(Constants.PropertiesFilePath);
		System.out.println("mobile "+ prop.getProperty("otp"));
		login.setMobile(prop.getProperty("mobile"));
		Map<String,Object> bodyParams=new HashMap<String,Object>();
		bodyParams.put("mobile", login.getMobile());
		bodyParams.put("otp",prop.getProperty("otp"));


		String payload=new Gson().toJson(bodyParams);
		System.out.println("****{POST}*********");


		Response response=ApiCall.postApiMethod(payload,ApiPaths.send_otp);


		ApiTestUtils.getAllApiResponse(response);
		ApiTestUtils.checkStatusCode(response, 200);
		ApiTestUtils.getStatusLineAssertTrue(response, "OK");

		String res =response.getBody().asString();
		ApiTestUtils.checkResponse(response, "Success");

		Utils.logPrint(res);


		getLogin(payload);

	}
	public void getLogin(String payload) {
		Response response=ApiCall.postApiMethod(payload,ApiPaths.login_otp);


		ApiTestUtils.getAllApiResponse(response);
		ApiTestUtils.checkStatusCode(response, 200);
		ApiTestUtils.getStatusLineAssertTrue(response, "OK");

		String res =response.getBody().asString();
		ApiTestUtils.checkResponse(response, "Success");

		Utils.logPrint(res);

	}


}
