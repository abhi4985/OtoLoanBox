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

public class GetQuote {
	
	@Test
	public void getMultiQuote() throws IOException {
		EmailLogin email = new EmailLogin();
		MultiQuoteFormField field =new MultiQuoteFormField();
		Response formResponse = field.getMultiQuoteFormFields();
		Response loginResponse = email.TestEmailLogin();
		Response loanStock = LoanStockList.getLoanStock();

			
		Map<String,Object> bodyParams=new HashMap<String,Object>();
		bodyParams.put("dealer_id", ApiTestUtils.getValueFromResponse(loginResponse, "data.user_data.user_id"));
		bodyParams.put("customer_region_id",ApiTestUtils.getValueFromAResponse(formResponse,"data[0].field_value[0].id"));
		bodyParams.put("olx_mrp","55555555");
		bodyParams.put("customer_area_id",ApiTestUtils.getValueFromAResponse(formResponse,"data[1].field_value[0].id"));
		bodyParams.put("make_id",ApiTestUtils.getValueFromAResponse(loanStock,"data[1].make_id"));
		bodyParams.put("model_id",ApiTestUtils.getValueFromAResponse(loanStock,"data[1].model_id"));
		bodyParams.put("version_id",ApiTestUtils.getValueFromAResponse(loanStock,"data[1].version_id"));
		bodyParams.put("make_year",ApiTestUtils.getValueFromAResponse(loanStock,"data[1].make_year"));
		bodyParams.put("plate_area_id",ApiTestUtils.getValueFromAResponse(formResponse,"data[6].field_value[0].id"));
		bodyParams.put("vehicle_type_id",ApiTestUtils.getValueFromAResponse(formResponse,"data[7].field_value[0].id"));
		bodyParams.put("brand_type",ApiTestUtils.getValueFromAResponse(formResponse,"data[8].field_value[0].id"));
		bodyParams.put("uses_type",ApiTestUtils.getValueFromAResponse(formResponse,"data[9].field_value[0].id"));
		bodyParams.put("insurance_type_id",ApiTestUtils.getValueFromAResponse(formResponse,"data[10].field_value[0].id"));
		bodyParams.put("tenure",ApiTestUtils.getValueFromAResponse(formResponse,"data[11].field_value[0]"));
		bodyParams.put("existing_customer",ApiTestUtils.getValueFromAResponse(formResponse,"data[12].field_value[0].id"));
		bodyParams.put("applicant_type_id",ApiTestUtils.getValueFromAResponse(formResponse,"data[13].field_value[0].id"));
		bodyParams.put("customer_age","25");
		bodyParams.put("occupation",ApiTestUtils.getValueFromAResponse(formResponse,"data[15].field_value[0].id"));
		bodyParams.put("distance_fee_id",ApiTestUtils.getValueFromAResponse(formResponse,"data[16].field_value[0].id"));
		bodyParams.put("residence_type",ApiTestUtils.getValueFromAResponse(formResponse,"data[17].field_value[0].id"));
		bodyParams.put("payment_type",ApiTestUtils.getValueFromAResponse(formResponse,"data[18].field_value[0].id"));
		bodyParams.put("mrp",ApiTestUtils.getValueFromAResponse(loanStock,"data[1].car_price"));
		bodyParams.put("price_upping_id",ApiTestUtils.getValueFromAResponse(formResponse,"data[21].field_value[0].id"));
		bodyParams.put("mrp_with_upping","56111110");
		bodyParams.put("dealer_price","55555555");
	
		String payload=new Gson().toJson(bodyParams);

		String Token = ApiTestUtils.getValueFromResponse(loginResponse, "data.token");
		Response resp=ApiCall.postApiWithHeader(payload,ApiPaths.multi_getquotes,Token);

		ApiTestUtils.getAllApiResponse(resp);
		ApiTestUtils.checkStatusCode(resp, 200);
		Utils.logPrint(ApiTestUtils.getValueFromAResponse(resp,"data[0].financier_id"));
		Utils.logPrint(ApiTestUtils.getAValueFromList(resp,"data.financier_id").toString());
		Utils.logPrint(ApiTestUtils.getListSize(resp,"data"));

}
	}
