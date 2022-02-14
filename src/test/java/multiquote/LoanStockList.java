package multiquote;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.girnarsoft.api.ApiCall;
import com.girnarsoft.api.ApiPaths;
import com.girnarsoft.api.ApiTestUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Utility.Constants;
import Utility.Utils;
import io.restassured.response.Response;
import utility.ExcelUtils;

public class LoanStockList {
	static String makeId; 
	static String modelId; 
	static String versionId; 
	static String makeYear; 
	static String carPrice; 
	Map<String, Object[]> loancarlist = new TreeMap<String, Object[]>();
	public void getLoanStock() throws IOException {


		Map<String,Object> bodyParams=new HashMap<String,Object>();
		bodyParams.put("dealer_id", Login.dealerid);
		bodyParams.put("search_text","");
		bodyParams.put("category_id","1");
		//bodyParams.put("page_no",1);
		bodyParams.put("car_status","[1]");
		//	bodyParams.put("not_included_car_id",[]);


		String payload=new Gson().toJson(bodyParams);

		Response response=ApiCall.postApiWithHeader(payload,ApiPaths.loan_stock,Login.token);

		ApiTestUtils.getAllApiResponse(response);
		ApiTestUtils.checkStatusCode(response, 200);
		Utils.logPrint(ApiTestUtils.getValueFromAResponse(response,"data[0].id"));
		Utils.logPrint(ApiTestUtils.getAValueFromList(response,"data.id").toString());
		Utils.logPrint(ApiTestUtils.getListSize(response,"data"));
		makeId=ApiTestUtils.getValueFromAResponse(response,"data[1].make_id");
		modelId=ApiTestUtils.getValueFromAResponse(response,"data[1].model_id");
		versionId=ApiTestUtils.getValueFromAResponse(response,"data[1].version_id");
		makeYear=ApiTestUtils.getValueFromAResponse(response,"data[1].make_year");
		carPrice=ApiTestUtils.getValueFromAResponse(response,"data[1].car_price");
		JSONObject obj = new JSONObject(response.asString());
		JSONArray arr = obj.getJSONArray("data");
		 loancarlist.put(""+ 0, new Object[] {"carTest","8"});
        loancarlist.put(""+ 1, new Object[] {"car_id","dealer_id","make_month","make_year","version_id",
        		"reg_no","reg_month","reg_year","km_driven","uc_colour","car_price","fuel_type","transmission",
        		"car_body_type","model","model_id","make_id","make","modelVersion"});
		for (int i = 0; i < arr.length(); ++i) {

			int id=arr.getJSONObject(i).getInt("id");
			int dealer_id=arr.getJSONObject(i).getInt("dealer_id");
			int make_month=arr.getJSONObject(i).getInt("make_month");
			int make_year=arr.getJSONObject(i).getInt("make_year");
			int version_id=arr.getJSONObject(i).getInt("version_id");
		//	String value = arr.getJSONObject(i).getString("reg_no") != null ? arr.getJSONObject(i).getString("reg_no"): null;
			Object reg_no=arr.getJSONObject(i).get("reg_no");
			Object reg_month=arr.getJSONObject(i).get("reg_month");
			Object reg_year=arr.getJSONObject(i).get("reg_year");
			int km_driven=arr.getJSONObject(i).getInt("km_driven");
			String uc_colour=arr.getJSONObject(i).getString("uc_colour");
			int car_price=arr.getJSONObject(i).getInt("car_price");
			String fuel_type=arr.getJSONObject(i).getString("fuel_type");
			String transmission=arr.getJSONObject(i).getString("transmission");
			String car_body_type=arr.getJSONObject(i).getString("car_body_type");
			String model=arr.getJSONObject(i).getString("model");
			int model_id=arr.getJSONObject(i).getInt("model_id");
			int make_id=arr.getJSONObject(i).getInt("make_id");
			String make=arr.getJSONObject(i).getString("make");
			String modelVersion=arr.getJSONObject(i).getString("modelVersion");
		//	System.out.println(arr.getJSONObject(i).get("reg_no"));
			
			 
			loancarlist.put(""+2+ i, new Object[] {id,dealer_id,make_month,make_year,version_id,reg_no,
					reg_month,reg_year,km_driven,uc_colour,car_price,fuel_type,transmission,car_body_type,model,
					model_id,make_id,make,modelVersion});

		}
		ExcelUtils.writeSheetData(Constants.ExcelTestData,"loanStock", loancarlist);

	}

}
