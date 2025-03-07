package smartbytes.sashirestassured.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A dirty simple program that reads an Excel file.
 * @author www.codejava.net
 *
 */
public class ExcelUtility {
//	public static void main(String[] args) {
//	String excelFilePath = "/Users/sys/Documents/restassured/Book1.xlsx";
//	String sheetName="Employees";
//	List<List<String>>	excelData= readExcelData(excelFilePath, sheetName);
//	
//	for (List<String> rowData : excelData) {
//		for(String cellData: rowData) {
//			System.out.print(cellData+"\t");
//		}
//		System.out.println("");
//	}
//	}
	/**
	 * Reads data from excel sheet
	 * @param excelFilePath	 excel file location
	 * @param sheetName		 sheet name in the excel
	 * @return
	 */
	public static List<List<String>> readExcelData(String excelFilePath,String sheetName){
	       
		List<List<String>> excelData = new ArrayList<List<String>>();
		List<String> rowData = null;
		
	        FileInputStream inputStream=null;
			try {
				inputStream = new FileInputStream(new File(excelFilePath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	         
	        Workbook workbook=null;
			try {
				workbook = new XSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        Sheet firstSheet = workbook.getSheet(sheetName);
	        Iterator<Row> iterator = firstSheet.iterator();
	        String cellValue="";
	        iterator.next();
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	            rowData = new ArrayList<String>();
	            
	            while (cellIterator.hasNext()) {
	            	cellValue="";
	                Cell cell = cellIterator.next();
	                switch (cell.getCellType()) {
	                    case Cell.CELL_TYPE_STRING:
	                        cellValue= cell.getStringCellValue();
	                        break;
	                    case Cell.CELL_TYPE_BOOLEAN:
	                        cellValue= String.valueOf(cell.getBooleanCellValue());
	                        break;
	                    case Cell.CELL_TYPE_NUMERIC:
	                        cellValue= String.valueOf(cell.getNumericCellValue());
	                        break;
	                }
	                //cellValue = cell.getStringCellValue();
	                rowData.add(cellValue);
	            }
	            excelData.add(rowData);
	        }
	         
	        try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	
		return excelData;
	} 
	
	public static void writeToExcel(String excelFilePath, String sheetName, List<Object[]> records) {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);
		int rowCount = 0;
		
		for(Object[] record : records) {
			
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			for(Object columnData : record) {
				Cell cell = row.createCell(columnCount++);
				if(columnData instanceof String) {
					cell.setCellValue((String) columnData);
				}
				else if(columnData instanceof Integer) {
					cell.setCellValue((Integer) columnData);
				}
				else if(columnData instanceof Boolean) {
					cell.setCellValue((Boolean) columnData);
				}
			}
		}
		try {
			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
			workbook.write(outputStream);
		}
		catch(Exception e) {
			System.err.println("Error occured while writing to file : " + e.getMessage());
		}
		
	}

}
