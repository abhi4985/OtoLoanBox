package com.girnarsoft.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Utility.Constants;

public class ReadExcelFile {

	@SuppressWarnings("resource")
	public static ArrayList<String> getData(String sheetName,String columnName,String testCase) throws IOException {

		ArrayList<String> dataList = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(Constants.ExcelTestData);// Excel file path
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets(); // get sheets
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i); //Identify Testcases column by scanning the entire 1st row

				Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator(); //row is collection of cells
				int k = 0;
				int coloumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase(columnName)) {
						coloumn = k;
					}
					k++;
				}
				System.out.println(coloumn);
				//once coloumn is identified then scan entire testcase column to identify testcase row
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testCase)) {
						//after you grab purchase testcase row = pull all the data of that row and feed into test
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell cell = cv.next();
							if (cell.getCellType() == CellType.STRING) {
								dataList.add(cell.getStringCellValue());						
							} else {
								dataList.add(NumberToTextConverter.toText(cell.getNumericCellValue()));								
							}
						}
					}
				}
			}
		}
		return dataList;
	}
	public void writeExcelData() {
		//Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook(); 

		//Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Employee Data");

		//This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
		data.put("2", new Object[] {1, "Amit", "Shukla"});
		data.put("3", new Object[] {2, "Lokesh", "Gupta"});
		data.put("4", new Object[] {3, "John", "Adwards"});
		data.put("5", new Object[] {4, "Brian", "Schultz"});

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
	}
	public void AddSheet() throws IOException {
		// Creating Workbook instances
		Workbook wb = new HSSFWorkbook();

		// An output stream accepts output bytes and

		OutputStream fileOut = new FileOutputStream(Constants.CreateExcelFile);

		// Now creating Sheets using sheet object
		Sheet sheet1 = wb.createSheet("Array");
		Sheet sheet2 = wb.createSheet("String");


		// Display message on console for successful

		System.out.println(
				"Sheets Has been Created successfully");

		// Closing the output stream
		wb.write(fileOut);
	}

	public static void writeExcelData(String file,String sheetName,ArrayList<String> rowlist,int column) throws IOException {
		// Create a workbook instances
		System.out.println("size"+rowlist.size());
		Workbook wb = new HSSFWorkbook();

		OutputStream os = new FileOutputStream(file);

		// Creating a sheet using predefined class provided by Apache POI
		Sheet sheet = wb.createSheet(sheetName);


		// Specific row number
	//	for(int j=0;j<column;j++) {
			
			Row row = sheet.createRow(column);
			for(int i=0;i<rowlist.size();i++) {
				// Specific cell number
				Cell cell = row.createCell(1+i);

				// putting value at specific position
				cell.setCellValue(rowlist.get(i));

			}
	//	}
		//Row row = sheet.createRow(column);
		

		// writing the content to Workbook
		wb.write(os);


	}
	public static void writeExcelData(String file,String sheetName,int i,int column) throws IOException {
		
		Workbook wb = new HSSFWorkbook();

		OutputStream os = new FileOutputStream(file);

		// Creating a sheet using predefined class provided by Apache POI
		Sheet sheet = wb.createSheet(sheetName);


		// Specific row number
		Row row = sheet.createRow(column);
	
			// Specific cell number
			Cell cell = row.createCell(1+i);

			// putting value at specific position
			cell.setCellValue(i);

		

		// writing the content to Workbook
		wb.write(os);


	}
	public static void createData(Map<String, Object[]> data, XSSFSheet sheet) {
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
	public static void createMultipleSheetData(Map<String, ArrayList<Object>> data, String path,String sheetName) throws IOException {
		Workbook wb = new HSSFWorkbook();

		// An output stream accepts output bytes and

		OutputStream fileOut = new FileOutputStream(path);
		//Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		Sheet sheet = wb.createSheet(sheetName);

		int rownum = 0;
	
		for (String key : keyset)
		{
		//	System.out.println("key"+key);
			Row row = sheet.createRow(rownum++);
			ArrayList<Object> objArr = data.get(key);
			//System.out.println("obj "+objArr);
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
	//	System.out.println("Sheets Has been Created successfully");

		// Closing the output stream
		wb.write(fileOut);
	}
	
}

