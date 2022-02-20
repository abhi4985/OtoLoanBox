package testcase;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import api.ReadExcelFile;

public class Exceldata {
	@Test
	public void getExcelData() throws IOException {
		ReadExcelFile d=new ReadExcelFile();
		ArrayList<String> data=d.getData("France","data","Country");
		//d.writeExcelData();
		//d.AddSheet();
		
		System.out.println("data "+ data);
		System.out.println(data.get(1));
	    System.out.println(data.get(2));
		System.out.println(data.get(3));
	}

}
