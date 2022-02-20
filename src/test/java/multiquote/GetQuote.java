package multiquote;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import Utility.Constants;
import Utility.Utils;
import api.ApiCall;
import api.ApiPaths;
import api.ApiTestUtils;
import api.JsonVariableReplacement;
import api.LoadJsonFile;
import commoncall.Login;
import io.restassured.response.Response;
import utility.ExcelUtils;
import utility.FileUtils;


public class GetQuote {
	static long olx_mrp=5000000000L;
	String customer_age="25";
	String mrp_with_upping="50000000000";
	String dealer_price="5000000000";

	
	public void getMultiQuote(int c) throws IOException {


		int testCar=Integer.parseInt(ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",0,1));	
		
		String bodyData=LoadJsonFile.handleJson(Constants.multiQuoteJson);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "dealerid",Login.dealerid);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "olx_mrp",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUF",11,c));	
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "customer_region_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUF",1,c));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "customer_area_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUF",3,c));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "makeid",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,16));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "modelid",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,15));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "versionid",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,4));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "makeyear",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,3));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "plate_area_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUF",2,c));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "vehicle_type_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUF",7,c));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "brand_type",MultiQuoteFormField.brand_type);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "uses_type",MultiQuoteFormField.uses_type);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "insurance_type_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUF",9,c));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "tenure",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUF",10,c));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "existing_customer",MultiQuoteFormField.existing_customer);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "applicant_type_id",MultiQuoteFormField.applicant_type_id);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "customer_age",ExcelUtils.readCellData(Constants.ExcelTestData,"multiQuoteData",2,1));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "occupation",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUF",2,c));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "distance_fee_id",MultiQuoteFormField.distance_fee_id);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "residence_type",MultiQuoteFormField.residence_type);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "payment_type",MultiQuoteFormField.payment_type);
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "carPrice",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,10));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "price_upping_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUF",12,c));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "mrp_with_upping",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUF",13,c));
		bodyData = JsonVariableReplacement.replaceJson(bodyData, "dealer_price",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUF",13,c));

		Response resp=ApiCall.postApiWithHeader(bodyData,ApiPaths.multi_getquotes,Login.token);

	//	ApiTestUtils.getAllApiResponse(resp);
		ApiTestUtils.checkStatusCode(resp, 200);
		Utils.logPrint(ApiTestUtils.getValueFromAResponse(resp,"data[0].financier_id"));
		Utils.logPrint(ApiTestUtils.getAValueFromList(resp,"data.financier_id").toString());
		Utils.logPrint(ApiTestUtils.getAValueFromList(resp,"data.financier_name").toString());
		Utils.logPrint(ApiTestUtils.getListSize(resp,"data"));
		FileUtils.createJsonFile("getQuote",resp);
		JSONObject obj = new JSONObject(resp.asString());
		JSONArray arr = obj.getJSONArray("data");
		for (int i = 0; i < arr.length(); ++i) {

			/*	if(arr.getJSONObject(i).getString("financier_name").equalsIgnoreCase("MUF")) {
				int installment=arr.getJSONObject(i).getInt("quotes_data.installment");
				int tenure=arr.getJSONObject(i).getInt("quotes_data.tenure");
				int total_payment_to_dealer=arr.getJSONObject(i).getInt("quotes_data.total_payment_to_dealer");
				int dp_amount=arr.getJSONObject(i).getInt("quotes_data.dp_amount");
				int total_dp=arr.getJSONObject(i).getInt("quotes_data.total_dp");
				int mrp=arr.getJSONObject(i).getInt("quotes_data.mrp");
				JSONObject objinner =  (JSONObject) arr.getJSONObject(i).get("quotes_data");

			//	Object mrp=objinner.get("quotes_data.mrp");
				System.out.println("  aaaa "+" "+objinner);
			}*/
			JSONObject objinner = (JSONObject) arr.getJSONObject(i).get("quotes_data");

			switch(arr.getJSONObject(i).getString("financier_name")){  
			case "Adira": 
				System.out.println("Adira");  
				System.out.println(objinner);
				break;  
			case "BFI":
				System.out.println("BFI");  
				System.out.println(objinner);
				
				break; 
			case "MPMF": 
				System.out.println("MPMF"); 
				System.out.println(objinner);
				
				break;  
			case "BCAMF":
				System.out.println("BCAMF");
				System.out.println(objinner);
				
				break; 
			case "Clipan": 
				System.out.println("Clipan"); 
				System.out.println(objinner);
				
				break;  
			case "DSF":
				System.out.println("DSF");
				System.out.println(objinner);
				
				break; 
			case "SMSF": 
				System.out.println("SMSF");  
				System.out.println(objinner);
				
				break;  
			case "KP": 
				System.out.println("KP"); 
				System.out.println(objinner);
				
				break; 
			case "SKBF": 
				System.out.println("SKBF");  
				System.out.println(objinner);
				
				break;  
			case "Trihamas": 
				System.out.println("Trihamas"); 
				System.out.println(objinner);
				
				break; 
			case "MUF": 
				String minDP=ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUF",15,c);
				System.out.println("muf "+minDP); 
				ExcelUtils.writSpecificCellData(Constants.OtoFinancerExcelFile,"MUF",25,c,minDP);
				System.out.println(objinner);
				break; 
			case "Maybank": 
				System.out.println("Maybank");
				System.out.println(objinner);
				break;  
			case "Buana": 
				System.out.println("Buana");
				System.out.println(objinner);
				break; 
			default:
				System.out.println("Not in finance");  
			}  
		}
	}
	@Test
	public void getQuote() throws IOException {
		int length=ExcelUtils.getNumberOfColumn(Constants.OtoFinancerExcelFile,"MUF",1);

		try {
			System.out.println("col length "+length);
			for(int col =1;col<length;col++) {
				getMultiQuote(col);
				
			}
			
		    TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
	}
	

}
