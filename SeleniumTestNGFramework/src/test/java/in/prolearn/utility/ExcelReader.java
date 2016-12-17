package in.prolearn.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class ExcelReader {
	@DataProvider(name="NewTours") //1
	public static String[][] storeCellDataForGivenY_MarkedTestCase(Method m) //3
			throws EncryptedDocumentException, InvalidFormatException, IOException{
		FileInputStream fis = new FileInputStream(".\\TestData\\SeleniumExcel.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet2");
		
		String testcasename = m.getName(); //Getting the method name using reflection call object. //4
		
		String[][] testData = new String[getNoOfGivenY_MarkedTestCase(testcasename)][getGivenTestCaseCellCount(testcasename)+1];
		int rowIndex =0;
		for(int i=1;i<sh.getPhysicalNumberOfRows();i++){
			Row r=sh.getRow(i);
			Cell c = r.getCell(1);
			Cell c1 = r.getCell(2);
			String val = c.getStringCellValue();
			String val2 = c1.getStringCellValue();
			int cellIndex =0;
			if(val.equalsIgnoreCase(testcasename)&&val2.equalsIgnoreCase("Y")){
				for(int cellDataIndex=3;cellDataIndex<r.getPhysicalNumberOfCells();cellDataIndex++){
					Cell c3 = r.getCell(cellDataIndex);
					testData[rowIndex][cellIndex++] = c3.getStringCellValue();
					
				}
				testData[rowIndex][cellIndex] = i+""; // To save the row number ("")added to convert the int to String
				rowIndex++;
			}
		}
		return testData;
	}
	
	public static int getNoOfGivenY_MarkedTestCase(String testcasename) 
			throws EncryptedDocumentException, InvalidFormatException, IOException{
		FileInputStream fis = new FileInputStream(".\\TestData\\SeleniumExcel.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int counter =0;
		Sheet sh = wb.getSheet("Sheet2");
		for(int i=1;i<sh.getPhysicalNumberOfRows();i++){
			Row r=sh.getRow(i);
			Cell c = r.getCell(1);
			Cell c1 = r.getCell(2);
			String val = c.getStringCellValue();
			String val2 = c1.getStringCellValue();
			if(val.equalsIgnoreCase(testcasename)&&val2.equalsIgnoreCase("Y")){
				counter++;
				}
			}
		return counter;
	}
	
	public static int getGivenTestCaseCellCount(String testcasename) throws EncryptedDocumentException, InvalidFormatException, IOException{
		FileInputStream fis = new FileInputStream(".\\TestData\\SeleniumExcel.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet2");
		int cellCount =0;
		for(int i=1;i<sh.getPhysicalNumberOfRows();i++){
			Row r=sh.getRow(i);
			Cell c = r.getCell(1);
			String val = c.getStringCellValue();
			if(val.equalsIgnoreCase(testcasename)){
				cellCount = r.getPhysicalNumberOfCells()-3;
				break;
			}
		}
		return cellCount;
	}
	
	public static void WriteExcel(String test, int rowNum, int colnum) {
		try {
			FileInputStream fis = new FileInputStream(".\\TestData\\SeleniumExcel.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s1 = wb.getSheet("Sheet2");
			Row r = s1.getRow(rowNum);
			Cell c = r.createCell(colnum);
			c.setCellValue("Pass");
			
			FileOutputStream fos = new FileOutputStream(".\\TestData\\SeleniumExcel.xlsx");
			wb.write(fos);
			fos.close();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
