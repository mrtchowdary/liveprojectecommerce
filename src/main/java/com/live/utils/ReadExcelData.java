package com.live.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.DataProvider;

import com.live.base.Base;

public class ReadExcelData extends Base{
	
	public static Workbook wb;
	
	public ReadExcelData() {
		
		File excel = new File("./src/main/java/com/live/data/testdata.xls");
		try {
			FileInputStream fis = new FileInputStream(excel);
//			wb = new XSSFWorkbook(fis);
			wb=new HSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println("ReadExcelData - Invalid data file");
		}
	}
	
	public String GetStringDataBySheetName(String sheetName, int row, int col){
		String value = wb.getSheet(sheetName).getRow(row).getCell(col).getStringCellValue();
		return value.equals("")?  "nodata":  value;
	}
	
	public double GetNumericDataBySheetName(String sheetName, int row, int col){
		return wb.getSheet(sheetName).getRow(row).getCell(col).getNumericCellValue();
	}

	public String GetStringDataBySheetIndex(int sheetIndex, int row, int col){
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(col).getStringCellValue();
	}
	
	public double GetNumericDataBySheetIndex(int sheetIndex, int row, int col){
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(col).getNumericCellValue();
	}
	
	public static Object[][] getTestData(String sheetName){
		Sheet sheet = wb.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
		}
		return data;
	}
	
	@DataProvider
	public Object[][] getDataFromLoginSheet(){
		Object data[][] = ReadExcelData.getTestData("Login");
		return data;
	}
	
	@DataProvider
	public Object[][] getDataFromInfoSheet(){
		Object data[][] = ReadExcelData.getTestData("Info");
		return data;
	}
}
