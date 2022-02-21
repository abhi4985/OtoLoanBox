package testClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import io.restassured.response.Response;
import utilsApi.ApiCall;
import utilsApi.ApiPaths;
import utilsApi.ApiTestUtils;
import utilsApi.ReadExcelFile;

public class TestData {
	
public static void main(String[] args) throws IOException {
	ReadExcelFile d=new ReadExcelFile();
	ArrayList<String> data=d.getData("login","islogin","y");
	//d.writeExcelData();
	//d.AddSheet();
	
	System.out.println("data "+ data);
	System.out.println(data.get(1));
    System.out.println(data.get(2));
    Map<String,Object> bodyParams=new HashMap<String,Object>();
	bodyParams.put("email", data.get(1));
	bodyParams.put("password",data.get(2));

	String payload=new Gson().toJson(bodyParams);
	System.out.println("****{POST}*****");

	Response response=ApiCall.postApiMethod(payload,ApiPaths.email_login);

	ApiTestUtils.getAllApiResponse(response);
	ApiTestUtils.checkStatusCode(response, 200);
	ApiTestUtils.getStatusLineAssertTrue(response, "OK");

	String res =response.getBody().asString();
	ApiTestUtils.checkResponse(response, "Success");
}
}

