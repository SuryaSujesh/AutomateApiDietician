package utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import resources.ExcelReader;

public class Testdata {
	
	static ExcelReader read=new ExcelReader("src/test/java/resources/LoginData.xlsx");
	
	public static HashMap<String,Object> InvalidloginPayload(String testcasename,String sheetname) throws IOException
	{
		List<String> data=read.getData(testcasename,sheetname);		
		HashMap<String,Object> invalidlogindata=new HashMap<String, Object>();
		invalidlogindata.put("password",data.get(1));
		invalidlogindata.put("userLoginEmail",data.get(2));	
		return invalidlogindata;
		
	}
		
	public static HashMap<String,Object> UserloginPayload(String testcasename,String sheetname) throws IOException
	{
		List<String> data=read.getData(testcasename,sheetname);		
		HashMap<String,Object> logindata=new HashMap<String, Object>();
		logindata.put("password",data.get(1));
		logindata.put("userLoginEmail",data.get(2));	
		return logindata;
		
	}
	
	public static HashMap<String,Object> Invalidloginpatient(String testcasename,String sheetname) throws IOException
	{
		List<String> data=read.getData(testcasename,sheetname);		
		HashMap<String,Object> invalidlogindata=new HashMap<String, Object>();
		invalidlogindata.put("password",data.get(1));
		invalidlogindata.put("userLoginEmail",data.get(2));	
		return invalidlogindata;
		
	}
	public static HashMap<String,Object> Validloginpatient(String testcasename,String sheetname) throws IOException
	{
		List<String> data=read.getData(testcasename,sheetname);		
		HashMap<String,Object> invalidlogindata=new HashMap<String, Object>();
		invalidlogindata.put("password",data.get(1));
		invalidlogindata.put("userLoginEmail",data.get(2));	
		return invalidlogindata;
		
	}
	
	
}
