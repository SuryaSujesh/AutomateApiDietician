package resources;
	
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Iterator;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.CellType;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.util.NumberToTextConverter;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelReader {
		FileInputStream fis;
		 public String path;
		 public ExcelReader(String path) 
		 
		 { 
			 this.path = path;
		 
		 } 
		
		public ArrayList<String> getData(String testcaseName,String sheetName) throws IOException
		{
			
				ArrayList<String> st=new ArrayList<String>();
				fis=new FileInputStream(path);
					
					XSSFWorkbook workbook=new XSSFWorkbook(fis);
					
					int sheets=workbook.getNumberOfSheets();
					for(int i=0;i<sheets;i++)
					{
						if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
								{
						XSSFSheet sheet=workbook.getSheetAt(i);
						
						Iterator<Row>  rows= sheet.iterator();
						Row firstrow= rows.next();
						Iterator<Cell> ce=firstrow.cellIterator();
						int k=0;
						int coloumn = 0;
					while(ce.hasNext())
					{
						Cell value=ce.next();
						
						if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
						{
							coloumn=k;
							
						}
						
					k++;
					}
					System.out.println(coloumn);
					
					while(rows.hasNext())
					{
						Row r=rows.next();
						if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName))
						{
							
						Iterator<Cell>  cv=r.cellIterator();
						while(cv.hasNext())
						{
						Cell c=	cv.next();
						if(c.getCellType()==CellType.STRING)
						{
						st.add(c.getStringCellValue());
						}
						else{
								
						st.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							
						}
						}
						}
					}										
					}			
					}
					
					return st;			
		}
	}


