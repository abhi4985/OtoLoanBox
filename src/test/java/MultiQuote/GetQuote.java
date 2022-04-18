package MultiQuote;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.ReportBaseClass;
import Utility.Constants;
import Utility.Utils;
import io.restassured.response.Response;
import utility.ExcelUtils;
import utility.FileUtils;
import utilsApi.ApiCall;
import utilsApi.ApiPaths;
import utilsApi.ApiTestUtils;
import utilsCommonClass.Login;


public class GetQuote extends ReportBaseClass{


	public void getMultiQuote(int c) throws IOException {


		ReportBaseClass.logger=ReportBaseClass.report.createTest("Test Multiquote");
		int testCar=Integer.parseInt(ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",0,1));	

		//String bodyData=LoadJsonFile.handleJson(Constants.multiQuoteJson);
		JSONObject bodyData = new JSONObject();
		bodyData.put("dealer_id",Login.dealerid);
		bodyData.put("customer_region_id",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",1,c));
		bodyData.put("olx_mrp",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",20,c));	
		bodyData.put("customer_area_id",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",2,c));
		bodyData.put("make_id",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,16));
		bodyData.put("model_id",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,15));
		bodyData.put("version_id",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,4));
		bodyData.put("make_year",ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,3));
		bodyData.put("plate_area_id",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",4,c));
		bodyData.put("vehicle_type_id",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",8,c));
		bodyData.put("brand_type",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",10,c));
		bodyData.put("uses_type",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",11,c));
		bodyData.put("insurance_type_id",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",18,c));
		bodyData.put("tenure",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",19,c));
		bodyData.put("existing_customer",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",12,c));
		bodyData.put("applicant_type_id",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",23,c));
		bodyData.put("customer_age",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",14,c));
		bodyData.put("occupation",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",3,c));
		bodyData.put("distance_fee_id",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",15,c));
		bodyData.put("residence_type",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",16,c));
		bodyData.put("payment_type",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",17,c));
		bodyData.put("mrp",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",20,c));
		//ExcelUtils.readCellData(Constants.ExcelTestData,"loanStock",testCar,10));
		bodyData.put("price_upping_id",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",21,c));
		bodyData.put("mrp_with_upping",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",22,c));
		bodyData.put("dealer_price",ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",24,c));


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
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				System.out.println(objinner);
				break;  
			case "BFI":
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				System.out.println(objinner);

				break; 
			case "MPMF": 
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				System.out.println(objinner);

				break;  
			case "BCAMF":
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				System.out.println(objinner);

				break; 
			case "Clipan": 
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				System.out.println(objinner);

				break;  
			case "DSF":
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				System.out.println(objinner);

				break; 
			case "SMSF": 
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				System.out.println(objinner);

				break;  
			case "KP": 
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				System.out.println(objinner);

				break; 
			case "SKBF": 
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				System.out.println(objinner);

				break;  
			case "Trihamas": 
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				System.out.println(objinner);

				break; 
			case "MUF": 
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				if (!objinner.isEmpty()) {
					
					int totaldp = objinner.getInt("total_dp");
					int dp_amount = objinner.getInt("dp_amount");
					int installment = objinner.getInt("installment");
					int mrp = objinner.getInt("mrp");
					int total_payment_to_dealer = objinner.getInt("total_payment_to_dealer");
					int tenure = objinner.getInt("tenure");
					System.out.println("td "+totaldp+" dpamount "+dp_amount+" inst "+installment);
					System.out.println(" mrp "+mrp+" pay to del "+total_payment_to_dealer+" ten "+tenure);

					String mintotalDP=ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",26,c);
					String instamount=ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",27,c);
					String numofinst=ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",28,c);
					String totalpayementtodealer=ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",29,c);
					String ltv=ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",30,c);
					String totalDP=ExcelUtils.readCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",31,c);

					System.out.println("td "+mintotalDP+" insamount "+instamount+" num inst "+numofinst);
					System.out.println(" tolpaytodel "+totalpayementtodealer+" ltv "+ltv+" totdp "+totalDP);
					ExcelUtils.writSpecificCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",36,c,totaldp);
					ExcelUtils.writSpecificCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",37,c,dp_amount);
					ExcelUtils.writSpecificCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",38,c,installment);
					ExcelUtils.writSpecificCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",39,c,mrp);
					ExcelUtils.writSpecificCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",40,c,total_payment_to_dealer);
					ExcelUtils.writSpecificCellData(Constants.MultiQutoeFinancerExcelFile,"MUF",41,c,tenure);

					Assert.assertEquals(totaldp, Integer.parseInt(mintotalDP));
					Assert.assertEquals(installment, Integer.parseInt(instamount));
					Assert.assertEquals(dp_amount, Integer.parseInt(totalDP));
					Assert.assertEquals(total_payment_to_dealer, Integer.parseInt(totalpayementtodealer));
					Assert.assertEquals(tenure,Integer.parseInt(numofinst));

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
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				System.out.println(objinner);
				break;  
			case "Buana": 
				System.out.println(arr.getJSONObject(i).getString("financier_name"));  
				System.out.println(objinner);
				break; 
			default:
				System.out.println("No financer");  
			}  
		}
		ReportBaseClass.logger.info("Test Multiquote is executed");
	}
	@Test
	public void getQuote() throws IOException {
		int length=ExcelUtils.getNumberOfColumn(Constants.MultiQutoeFinancerExcelFile,"MUF",1);

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
