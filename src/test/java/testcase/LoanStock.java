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

public class LoanStock {
	@Test
	public void getLoanStock() throws IOException {
		EmailLogin email = new EmailLogin();
		int page=1;
		Response response = email.TestEmailLogin();

		Map<String,Object> bodyParams=new HashMap<String,Object>();
		bodyParams.put("dealer_id", ApiTestUtils.getValueFromResponse(response, "data.user_data.user_id"));
		bodyParams.put("search_text","");
		bodyParams.put("category_id","1");
		bodyParams.put("page_no",page);
		bodyParams.put("car_status","[1]");
		//	bodyParams.put("not_included_car_id",[]);


		String payload=new Gson().toJson(bodyParams);

		String Token = ApiTestUtils.getValueFromResponse(response, "data.token");
		Response resp=ApiCall.postApiWithHeader(payload,ApiPaths.loan_stock,Token);

	//	ApiTestUtils.getAllApiResponse(resp);
		ApiTestUtils.checkStatusCode(resp, 200);
		Utils.logPrint(ApiTestUtils.getValueFromAResponse(resp,"data[0].id"));
		Utils.logPrint(ApiTestUtils.getAValueFromList(resp,"data.id").toString());
		Utils.logPrint(ApiTestUtils.getListSize(resp,"data"));
		Utils.logPrint(ApiTestUtils.getValueFromAResponse(resp,"pagination.next_page"));
		if(ApiTestUtils.getBooleanResponse(resp,"pagination.next_page")) {
		 resp=ApiCall.postApiWithHeader(payload,ApiPaths.loan_stock,Token);

			//ApiTestUtils.getAllApiResponse(resp);
			ApiTestUtils.checkStatusCode(resp, 200);
			Utils.logPrint(ApiTestUtils.getAValueFromList(resp,"data.id").toString());
		}

	}
}