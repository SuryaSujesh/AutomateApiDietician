package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	
	public static String getValue(String key) throws IOException {
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
	prop.load(fis);
    return prop.getProperty(key);
	}

	public static RequestSpecification requestSpec() throws IOException {	
		if(req==null) 
		{
		PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
		
		req = new RequestSpecBuilder()
				.setBaseUri(getValue("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return req;
		}
		
		return req;
		
	}
	

}
