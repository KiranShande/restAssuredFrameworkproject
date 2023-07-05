package commonFunctionPackage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;

public class utility_CommonFunctions {
	@AfterTest
	
	public static void evidencecreater(String filename,String requestBody,String responseBody)throws IOException{
	File newfile=new File("C:\\Users\\Kiran\\OneDrive\\Desktop\\rest assured\\"+filename+".txt"); 
	System.out.println("a new file is creeated to save requestand reposne of API:" +newfile.getName());
	
	FileWriter datawrite=new FileWriter(newfile);
	datawrite.write("request body :"+requestBody+"\n\n");
	datawrite.write("response body :"+responseBody);
	datawrite.close();
	System.out.println("requestbody and responsebody are saved in file :"+newfile.getName());
	
	}
	
	public static ArrayList<String> readdataexcel(String sheetname,String testcasename) throws IOException
	{
		ArrayList<String> ArrayData=new ArrayList<String>();
		// step 1 : create the object file input stream
		FileInputStream fis=new FileInputStream("C:\\Users\\Kiran\\OneDrive\\Desktop\\kiran_API_TestData.xlsx");
		//step 2 : access the excelthefile
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		// step 3 : access the sheet name
		int countofsheet=workbook.getNumberOfSheets();
		for(int i=0;i<countofsheet;i++)
		{
			String filesheetname=workbook.getSheetName(i);
			if(filesheetname.equalsIgnoreCase(sheetname))
			{
				// step 4 : access the row from where the data is suppose to fetch
				XSSFSheet sheet=workbook.getSheetAt(i);
				Iterator<Row> rows=sheet.iterator();
				//Row r=rows.next();
				while(rows.hasNext())
				{
					Row r2=rows.next();
					if(r2.getCell(0).getStringCellValue().equalsIgnoreCase(testcasename))
					{
						Iterator<Cell> cellvalues=r2.cellIterator();
						while(cellvalues.hasNext()) 
						{
							String testdata=cellvalues.next().getStringCellValue();
									ArrayData.add(testdata);
							
						}
					}
				}
			}
		}
		workbook.close();
		return ArrayData;
			
	}
	
}
