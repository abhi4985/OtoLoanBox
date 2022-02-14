package multiquote;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.girnarsoft.api.ApiCall;
import com.girnarsoft.api.ApiPaths;
import com.girnarsoft.api.ApiTestUtils;
import com.girnarsoft.api.JsonVariableReplacement;
import com.girnarsoft.api.LoadJsonFile;
import com.google.gson.Gson;

import Utility.Constants;
import Utility.Utils;
import io.restassured.response.Response;
import testcase.EmailLogin;
import utility.ExcelUtils;
import utility.Operation;


public class GetQuote {
	static long olx_mrp=5000000000L;
	String customer_age="25";
	String mrp_with_upping="50000000000";
	String dealer_price="5000000000";
	
	@Test
	public void getMultiQuote() throws IOException {
	

			
		String bodyData=LoadJsonFile.handleJson(Constants.multiQuoteJson);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "dealerid",Login.dealerid);
	    bodyData = JsonVariableReplacement.replaceJson(bodyData, "olx_mrp",ExcelUtils.readCellData(Constants.ExcelTestData,"multiQuoteData",0,1));	
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "customer_region_id",MultiQuoteFormField.customer_region_id);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "customer_area_id",MultiQuoteFormField.customer_area_id);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "makeid",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",1,15));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "modelid",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",1,14));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "versionid",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",1,4));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "makeyear",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",1,3));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "plate_area_id",MultiQuoteFormField.plate_area_id);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "vehicle_type_id",MultiQuoteFormField.vehicle_type_id);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "brand_type",MultiQuoteFormField.brand_type);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "uses_type",MultiQuoteFormField.uses_type);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "insurance_type_id",MultiQuoteFormField.insurance_type_id);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "tenure",MultiQuoteFormField.tenure);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "existing_customer",MultiQuoteFormField.existing_customer);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "applicant_type_id",MultiQuoteFormField.applicant_type_id);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "customer_age",ExcelUtils.readCellData(Constants.ExcelTestData,"multiQuoteData",2,1));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "occupation",MultiQuoteFormField.occupation);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "distance_fee_id",MultiQuoteFormField.distance_fee_id);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "residence_type",MultiQuoteFormField.residence_type);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "payment_type",MultiQuoteFormField.payment_type);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "carPrice",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",1,10));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "price_upping_id",MultiQuoteFormField.price_upping_id);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "mrp_with_upping",Operation.priceUppingCalculation(CarMarketMrp.carMarketMrp, Integer.parseInt(MultiQuoteFormField.price_upping_id)));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "dealer_price",ExcelUtils.readCellData(Constants.ExcelTestData,"multiQuoteData",1,1));
	
		Response resp=ApiCall.postApiWithHeader(bodyData,ApiPaths.multi_getquotes,Login.token);

		ApiTestUtils.getAllApiResponse(resp);
		ApiTestUtils.checkStatusCode(resp, 200);
		Utils.logPrint(ApiTestUtils.getValueFromAResponse(resp,"data[0].financier_id"));
		Utils.logPrint(ApiTestUtils.getAValueFromList(resp,"data.financier_id").toString());
		Utils.logPrint(ApiTestUtils.getAValueFromList(resp,"data.financier_name").toString());
		Utils.logPrint(ApiTestUtils.getListSize(resp,"data"));

}
	}
