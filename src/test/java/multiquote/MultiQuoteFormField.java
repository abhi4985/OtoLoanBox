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

public class MultiQuoteFormField {
	@Test
	public Response getMultiQuoteFormFields() throws IOException {
		EmailLogin email = new EmailLogin();
		Response response = email.TestEmailLogin();

		Map<String,Object> bodyParams=new HashMap<String,Object>();
		bodyParams.put("dealer_id", ApiTestUtils.getValueFromResponse(response, "data.user_data.user_id"));

		String payload=new Gson().toJson(bodyParams);

		String Token = ApiTestUtils.getValueFromResponse(response, "data.token");
		Response resp=ApiCall.postApiWithHeader(payload,ApiPaths.multi_quote_form,Token);

		ApiTestUtils.getAllApiResponse(resp);
		ApiTestUtils.checkStatusCode(resp, 200);
		Utils.logPrint(ApiTestUtils.getValueFromAResponse(resp,"data[0].id"));
		Utils.logPrint(ApiTestUtils.getAValueFromList(resp,"data.id").toString());
		Utils.logPrint(ApiTestUtils.getListSize(resp,"data"));
		return resp;

	}

}
