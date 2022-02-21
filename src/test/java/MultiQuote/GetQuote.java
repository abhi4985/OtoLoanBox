package MultiQuote;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.Constants;
import Utility.Utils;
import io.restassured.response.Response;
import utility.ExcelUtils;
import utility.FileUtils;
import utilsApi.ApiCall;
import utilsApi.ApiPaths;
import utilsApi.ApiTestUtils;
import utilsCommonClass.Login;


public class GetQuote {


	public void getMultiQuote(int c) throws IOException {


		int testCar=Integer.parseInt(ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",0,1));	

		//String bodyData=LoadJsonFile.handleJson(Constants.multiQuoteJson);
		JSONObject bodyData = new JSONObject();
		bodyData.put("dealer_id",Login.dealerid);
		bodyData.put("customer_region_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",1,c));
		bodyData.put("olx_mrp",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",20,c));	
		bodyData.put("customer_area_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",2,c));
		bodyData.put("make_id",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,16));
		bodyData.put("model_id",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,15));
		bodyData.put("version_id",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,4));
		bodyData.put("make_year",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,3));
		bodyData.put("plate_area_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",4,c));
		bodyData.put("vehicle_type_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",8,c));
		bodyData.put("brand_type",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",10,c));
		bodyData.put("uses_type",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",11,c));
		bodyData.put("insurance_type_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",18,c));
		bodyData.put("tenure",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",19,c));
		bodyData.put("existing_customer",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",12,c));
		bodyData.put("applicant_type_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",23,c));
		bodyData.put("customer_age",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",14,c));
		bodyData.put("occupation",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",3,c));
		bodyData.put("distance_fee_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",15,c));
		bodyData.put("residence_type",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",16,c));
		bodyData.put("payment_type",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",17,c));
		bodyData.put("mrp",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",20,c));
				//ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,10));
		bodyData.put("price_upping_id",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",21,c));
		bodyData.put("mrp_with_upping",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",22,c));
		bodyData.put("dealer_price",ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",24,c));


		Response resp=ApiCall.postApiWithHeader(bodyData.toString(),ApiPaths.multi_getquotes,Login.token);

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
				if (!objinner.isEmpty()) {
	//				{"total_dp":17165000,"dp_amount":13000000,"installment":4468000,"mrp":65000000,"total_payment_to_dealer":48250000,"tenure":12}
					int totaldp = objinner.getInt("total_dp");
					int dp_amount = objinner.getInt("dp_amount");
					int installment = objinner.getInt("installment");
					int mrp = objinner.getInt("mrp");
					int total_payment_to_dealer = objinner.getInt("total_payment_to_dealer");
					int tenure = objinner.getInt("tenure");
				System.out.println("td "+totaldp+" dpamount "+dp_amount+" inst "+installment);
				System.out.println(" mrp "+mrp+" pay to del "+total_payment_to_dealer+" ten "+tenure);
			
					String mintotalDP=ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",26,c);
					String instalmentamount=ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",27,c);
					String numofinstallment=ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",28,c);
					String totalpayementtodealer=ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",29,c);
					String ltv=ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",30,c);
					String totalDP=ExcelUtils.readCellData(Constants.OtoFinancerExcelFile,"MUFMQ",31,c);
					
					System.out.println("td "+mintotalDP+" insamount "+instalmentamount+" num inst "+numofinstallment);
					System.out.println(" tolpaytodel "+totalpayementtodealer+" ltv "+ltv+" totdp "+totalDP);
					ExcelUtils.writSpecificCellData(Constants.OtoFinancerExcelFile,"MUFMQ",36,c,totaldp);
					ExcelUtils.writSpecificCellData(Constants.OtoFinancerExcelFile,"MUFMQ",37,c,dp_amount);
					ExcelUtils.writSpecificCellData(Constants.OtoFinancerExcelFile,"MUFMQ",38,c,installment);
					ExcelUtils.writSpecificCellData(Constants.OtoFinancerExcelFile,"MUFMQ",39,c,mrp);
					ExcelUtils.writSpecificCellData(Constants.OtoFinancerExcelFile,"MUFMQ",40,c,total_payment_to_dealer);
					ExcelUtils.writSpecificCellData(Constants.OtoFinancerExcelFile,"MUFMQ",41,c,tenure);
					Assert.assertEquals(totaldp, Integer.parseInt(mintotalDP));
					Assert.assertEquals(installment, Integer.parseInt(instalmentamount));
					Assert.assertEquals(dp_amount, Integer.parseInt(totalDP));
					Assert.assertEquals(total_payment_to_dealer, Integer.parseInt(totalpayementtodealer));
					Assert.assertEquals(tenure,Integer.parseInt(numofinstallment));
					
					/*if(totaldp==Integer.parseInt(totalDP)) {
					System.out.println("total dp match");
				//	ExcelUtils.writSpecificCellData(Constants.OtoFinancerExcelFile,"MUFMQ",36,c,totaldp);
					}else { 
						System.out.println("else total dp match");
					}
						if(tenure==Integer.parseInt(numofinstallment)) {
					
						System.out.println("num of inst match");
					//	ExcelUtils.writSpecificCellData(Constants.OtoFinancerExcelFile,"MUFMQ",41,c,totaldp);
						}
						else {
							System.out.println("else num of inst match");
						}*/
				
				//	System.out.println(objinner);
				}
				break; 
			case "Maybank": 
				System.out.println("Maybank");
				System.out.println(objinner);
				if (!objinner.isEmpty()) {
					System.out.println("if");
				}else {
					System.out.println("else");
				}
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
		int length=ExcelUtils.getNumberOfColumn(Constants.OtoFinancerExcelFile,"MUFMQ",1);

		try {
			System.out.println("col length "+length);
			for(int col=1;col<length;col++) {
				getMultiQuote(col);

			}

			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}

	}


}
