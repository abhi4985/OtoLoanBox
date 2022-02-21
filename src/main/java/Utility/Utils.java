package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class Utils {

	public static Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}
	public static void CreatingPropertiesFile() throws IOException{

		//Instantiating the properties file
		Properties props = new Properties();
		//Populating the properties file

		props.put("mobile", "8888888888");

		FileOutputStream outputStrem = new FileOutputStream(Constants.PropertiesFilePath);
		//Storing the properties file
		props.store(outputStrem, "This is a sample properties file");
		System.out.println("Properties file created......");
	}

	public static void logPrint(String log) {
		System.out.println("Log " +log);

	}
	public static void getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
	}
}

