package testClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.google.gson.Gson;

import io.restassured.response.Response;
import utility.InvokeMail;
import utility.SendMail;
import utilsApi.ApiCall;
import utilsApi.ApiPaths;
import utilsApi.ApiTestUtils;
import utilsApi.ReadExcelFile;

public class TestData {

	public static void main(String[] args) throws IOException, AddressException, MessagingException {
		ReadExcelFile d=new ReadExcelFile();
		ArrayList<String> data=d.getData("login","islogin","y");
	

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
        System.out.println("status "+ApiTestUtils.getValueFromResponse(response, "status"));
        System.out.println(ApiTestUtils.getValueFromResponse(response, "message"));
		String res =response.getBody().asString();
		ApiTestUtils.checkResponse(response, "Success");

	//	SendMail.send(InvokeMail.server,InvokeMail.from, InvokeMail.to, InvokeMail.subject, 
		//		InvokeMail.messageBody, InvokeMail.attachmentPath, InvokeMail.attachmentName);
	}
}

