package multiquote;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.girnarsoft.api.ApiCall;
import com.girnarsoft.api.ApiPaths;
import com.girnarsoft.api.ApiTestUtils;
import com.google.gson.Gson;

import Utility.Constants;
import Utility.Utils;
import io.restassured.response.Response;
import testcase.EmailLogin;

public class GetQuoteDynamic2 {
	
	EmailLogin email;
	MultiQuoteFormField field;
	
	int id,makeid,versionid,modelid;
	String label="";
	
	Map<String, Object[]> data = new TreeMap<String, Object[]>();
	 
	 ArrayList<String> arraylist= new ArrayList<String>();
	
	@Test
	public void getMultiQuote() throws IOException {
		email = new EmailLogin();
		field =new MultiQuoteFormField();
		//Response formResponse = field.getMultiQuoteFormFields();
		Response loginResponse = email.TestEmailLogin();
	//	Response loanStock = LoanStockList.getLoanStock();

		
		
		
     //   data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
       
		
        //////////////////////// read sheet ///////////////////
        
		  JSONObject obj = new JSONObject(""/*+loanStock.asString()*/);
	      /*  String pageName = obj.getJSONObject("pageInfo").getString("pageName");

	        System.out.println(pageName);*/
		  
		  

	        JSONArray arr = obj.getJSONArray("data");
	        System.out.println("length::"+arr.length());
	      //  List<JSONObject> myJSONObjects = new  ArrayList<JSONObject> ();
	        
	        
	        XSSFWorkbook workbook = new XSSFWorkbook(); 
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Report");
	        int rowCount = 0;
	        
	        
	       for (int i = 0; i < arr.length(); ++i) {
	    	   
	    	 //  JSONObject objn = new JSONObject();
	          
	    	   id=arr.getJSONObject(i).getInt("id");
	    	   makeid=arr.getJSONObject(i).getInt("make_id");
	    	   versionid=arr.getJSONObject(i).getInt("version_id");
	    	   modelid=arr.getJSONObject(i).getInt("model_id");
	    	   
	            System.out.println(arr.getJSONObject(i).getInt("id"));
	            
	            System.out.println(arr.getJSONObject(i).getInt("make_id"));
	            
	            System.out.println(arr.getJSONObject(i).getInt("version_id"));
	            
	            System.out.println(arr.getJSONObject(i).getInt("model_id"));
	            
	            JSONArray arr1 =arr.getJSONObject(i).getJSONArray("app_data");
	            
	            System.out.println("length of appdata::"+arr1.length());
	            
	          /*  objn.put("id",id);
	            
	            objn.put("makeid",makeid);
	            objn.put("versionid",versionid);*/
	            
//	            Row row = sheet.createRow(rowCount++);
//	            Cell cell1 = row.createCell(0);
//	            cell1.setCellValue(id);
//	            Cell cell2 = row.createCell(1);
//	            cell2.setCellValue(makeid);
//	            Cell cell3 = row.createCell(2);
//	            cell3.setCellValue(versionid);
	            
	            for(int j = 0; j < arr1.length(); j++)
	            {
	            	System.out.println(arr1.getJSONObject(j).get("label"));
	            
	            
	            	label=label+arr1.getJSONObject(j).get("label");
	            }
	            
	           
	            
	            //Cell cell4 = row.createCell(3);
	           // cell4.setCellValue(label);
	            data.put(""+ i, new Object[] {id, makeid,modelid,versionid});
	            label="";
	     
	            
	           
			
		}
	       
	       createData(data,sheet);
	       
//	       try (FileOutputStream outputStream = new FileOutputStream("Report.xlsx")) {
//	            workbook.write(outputStream);
//	        }
	       
	  
	       try
	        {
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(Constants.CreateExcelFile));
	            workbook.write(out);
	            out.close();
	            System.out.println(Constants.CreateExcelFile+" written successfully on disk.");
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
      
      //////////////////////   read sheet ////////////////////////
		
		
		
	   	/******************************* Get all the data from email Login api **************************/
			
			//String dealer_id=ApiTestUtils.getValueFromResponse(loginResponse, "data.user_data.user_id");
			
			//String Token = ApiTestUtils.getValueFromResponse(loginResponse, "data.token");
			
			/////////////////// Writing in Excel Sheet ///////////////////
			

			
	}
	public void createData(Map<String, Object[]> data, XSSFSheet sheet) {
		 //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
	}

}
	