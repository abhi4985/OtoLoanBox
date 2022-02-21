package MultiQuote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Utility.Constants;
import Utility.Utils;
import io.restassured.response.Response;
import testClasses.EmailLogin;
import utility.Operation;
import utilsApi.ApiCall;
import utilsApi.ApiPaths;
import utilsApi.ApiTestUtils;
import utilsApi.ReadExcelFile;
import utilsCommonClass.Login;

public class MultiQuoteFormField {
	static String customer_region_id;
	static String customer_area_id;
	static String plate_area_id;
	static String vehicle_type_id;
	static String brand_type;
	static String uses_type;
	static String insurance_type_id;
	static String tenure;
	static String existing_customer;
	static String applicant_type_id;
	static String occupation;
	static String distance_fee_id;
	static String residence_type;
	static String payment_type;
	static String price_upping_id;
	static int price_with_upping;
	
	Map<String, ArrayList<Object>> data = new TreeMap<String, ArrayList<Object>>();

	@Test
	public void getMultiQuoteFormFields() throws IOException {
		

		Map<String,Object> bodyParams=new HashMap<String,Object>();
		bodyParams.put("dealer_id", Login.dealerid);

		String payload=new Gson().toJson(bodyParams);

		Response resp=ApiCall.postApiWithHeader(payload,ApiPaths.multi_quote_form,Login.token);

		ApiTestUtils.getAllApiResponse(resp);
		ApiTestUtils.checkStatusCode(resp, 200);
		customer_region_id=ApiTestUtils.getValueFromAResponse(resp,"data[0].field_value[0].id");
		customer_area_id=ApiTestUtils.getValueFromAResponse(resp,"data[1].field_value[0].id");
		plate_area_id=ApiTestUtils.getValueFromAResponse(resp,"data[6].field_value[0].id");
		vehicle_type_id=ApiTestUtils.getValueFromAResponse(resp,"data[7].field_value[0].id");
		brand_type=ApiTestUtils.getValueFromAResponse(resp,"data[8].field_value[0].id");
		uses_type=ApiTestUtils.getValueFromAResponse(resp,"data[9].field_value[0].id");
		insurance_type_id=ApiTestUtils.getValueFromAResponse(resp,"data[10].field_value[0].id");
		tenure=ApiTestUtils.getValueFromAResponse(resp,"data[11].field_value[0]");
		existing_customer=ApiTestUtils.getValueFromAResponse(resp,"data[12].field_value[0].id");
		applicant_type_id=ApiTestUtils.getValueFromAResponse(resp,"data[13].field_value[0].id");
		occupation=ApiTestUtils.getValueFromAResponse(resp,"data[15].field_value[0].id");
		distance_fee_id=ApiTestUtils.getValueFromAResponse(resp,"data[16].field_value[0].id");
		residence_type=ApiTestUtils.getValueFromAResponse(resp,"data[17].field_value[0].id");
		payment_type=ApiTestUtils.getValueFromAResponse(resp,"data[18].field_value[0].id");
		price_upping_id=ApiTestUtils.getValueFromAResponse(resp,"data[21].field_value[2].upping");
		Utils.logPrint(ApiTestUtils.getValueFromAResponse(resp,"data[0].id"));
		Utils.logPrint(ApiTestUtils.getAValueFromList(resp,"data.id").toString());
		Utils.logPrint(ApiTestUtils.getListSize(resp,"data"));
		
		//System.out.println("upping id "+Operation.priceUppingCalculation(GetQuote.olx_mrp, Integer. parseInt(price_upping_id)));
		//System.out.println(id);
		

		  JSONObject obj = new JSONObject(resp.asString());
	     
		  JSONArray arr = obj.getJSONArray("data");
		  ArrayList<String>column = new ArrayList<String>();
		  ArrayList<Object>row = new ArrayList<Object>();
		  
		  for(int i = 0;i<arr.length();i++) {
			  JSONObject object= arr.getJSONObject(i);
				System.out.println(object.get("field_name")+"     "+i);
				row.add((String) object.get("field_name"));
				//column.add((String) object.get("field_name"));
				JSONArray innerarr= object.getJSONArray("field_value");
				if (innerarr.length() == 0) {
				//	System.out.println("empty array" + i);
					data.put(""+i, row );
				}else {
					try {
						 for(int j=0;j<innerarr.length();j++) {
					//		 System.out.println("no empty array" + i);
							  JSONObject innerobject= innerarr.getJSONObject(j);
					//			System.out.println(innerobject.get("name")+"          "+j);
								row.add(innerobject.get("name"));
							//	ReadExcelFile.writeExcelData(Constants.CreateMultiQuoteExcelFile, "MultiQuote", row, j);
								 
							//	 data.put(""+k, row );
								 
								
							//	 row=new ArrayList<String>();
								 
						 }	
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
					System.out.println("row "+ row );
					 data.put(""+i, row );
					// ReadExcelFile.writeExcelData(Constants.CreateMultiQuoteExcelFile, "MultiQuote", row, i);
					 row=new ArrayList<Object>();
					 
				
				 ReadExcelFile.createMultipleSheetData(data,Constants.CreateMultiQuoteExcelFile,"MultiQuote");
			 
		  }
	       	

	}

}
