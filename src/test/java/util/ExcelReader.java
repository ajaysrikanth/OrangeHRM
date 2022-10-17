package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String reader(String sheetName, int rowNo, int columnNo) throws IOException {
		
		File f = new File("C:\\Users\\SRUTHI\\eclipse-workspace\\OrangeHRM\\data.xlsx");
		
		FileInputStream fis = new FileInputStream(f);
	
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = wb.getSheet(sheetName);
		
		//XSSFRow row = sheet.getRow(0);
		
		String data = sheet.getRow(rowNo).getCell(columnNo).getStringCellValue();
		
		System.out.println(data);
		
		wb.close();
		
		return data;
	}

}
