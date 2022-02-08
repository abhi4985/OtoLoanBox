package multiquote;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.girnarsoft.api.ApiCall;
import com.girnarsoft.api.ApiPaths;
import com.girnarsoft.api.ApiTestUtils;
import com.google.gson.Gson;

import Utility.Utils;
import io.restassured.response.Response;
import testcase.EmailLogin;

public class LoanStockList {
	@Test
	public static Response getLoanStock() throws IOException {
		EmailLogin email = new EmailLogin();
		Response loginResponse = email.TestEmailLogin();

		Map<String,Object> bodyParams=new HashMap<String,Object>();
		bodyParams.put("dealer_id", ApiTestUtils.getValueFromResponse(loginResponse, "data.user_data.user_id"));
		bodyParams.put("search_text","");
		bodyParams.put("category_id","1");
		bodyParams.put("page_no",1);
		bodyParams.put("car_status","[1]");
		//	bodyParams.put("not_included_car_id",[]);


		String payload=new Gson().toJson(bodyParams);

		String Token = ApiTestUtils.getValueFromResponse(loginResponse, "data.token");
		Response response=ApiCall.postApiWithHeader(payload,ApiPaths.loan_stock,Token);

		ApiTestUtils.getAllApiResponse(response);
		ApiTestUtils.checkStatusCode(response, 200);
		Utils.logPrint(ApiTestUtils.getValueFromAResponse(response,"data[0].id"));
		Utils.logPrint(ApiTestUtils.getAValueFromList(response,"data.id").toString());
		Utils.logPrint(ApiTestUtils.getListSize(response,"data"));
		return response;

	}

}
