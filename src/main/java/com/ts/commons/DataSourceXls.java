package com.ts.commons;

import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.read.biff.BiffException;

public class DataSourceXls extends DataSource{
	
	final int defaultRowToBeginRun = 1;
	final int defaultSheetToBeginRun = 0;
	
	public DataSourceXls(String path) throws BiffException, IOException {
		super(path);
	}
	
	public Object[][] getData() throws BiffException, IOException {
		return getData( defaultSheetToBeginRun, defaultRowToBeginRun);
	}
	
	public Object[][] getData(String sheetName) throws BiffException, IOException {		
		return getData(getIndexBySheetName(sheetName), defaultRowToBeginRun);
	}
	
	public Object[][] getData(int sheetnum) throws BiffException, IOException {		
		return getData(sheetnum, defaultRowToBeginRun);
	}
	
	public Object[][] getData(String sheetName, int rowToBeginRead) throws BiffException, IOException {		
		return getData(getIndexBySheetName(sheetName), rowToBeginRead);
	}
	
	public Object[][] getData(int sheetNum, int rowToBeginRead) throws BiffException, IOException {
		return getData(sheetNum, rowToBeginRead, -1);		
	}
	
	public Object[][] getData(String sheetName, int rowToBeginRead, int rowToEndRead) throws BiffException, IOException {
		return getData(getIndexBySheetName(sheetName), rowToBeginRead, rowToEndRead);
	}
		
	public Object[][] getData(int sheetNum, int rowToBeginRead, int rowToEndRead) throws BiffException, IOException {
		Sheet sheet = book.getSheet(sheetNum);
		int rowToEnd;

		if (rowToEndRead < 1) {
			rowToEnd = sheet.getRows();
		} else {
			rowToEnd = ((sheet.getRows() - (sheet.getRows() - rowToEndRead))) + 1;
		}

		if (rowToEnd < rowToBeginRead) {
			throw new RuntimeException("Row To start read = " + rowToBeginRead + ", Row to end read = " + rowToEnd 
										+ ". Row to end read must be higher than Row To start read.");
		}

		Object[][] data = new Object[rowToEnd - rowToBeginRead][sheet.getColumns()];

		for (int row = rowToBeginRead; row < rowToEnd; row++) {
			for (int colum = 0; colum < sheet.getColumns(); colum++) {
				Cell cell = sheet.getCell(colum, row);
				if (isEmptyRow(sheet, row, sheet.getColumns())) {
					break;
				} else {
					data[row - (rowToBeginRead)][colum] = String.valueOf(cell.getContents());
				}
			}
		}

		closeBook();

		if (data.length == 0) {
			throw new RuntimeException("There is not data to return, the default row to begin run is "
										+ defaultRowToBeginRun);
		} else {
			return data;
		}
	}
	
	private int getIndexBySheetName(String sheetNameToGetIndex) throws BiffException, IOException{
		Sheet[] allSheets = book.getSheets();
		int exist = -1;

		for (int i = 0; i < allSheets.length; i++) {
			if (allSheets[i].getName().trim().equalsIgnoreCase(sheetNameToGetIndex)) {
				exist = i;
				break;
			}
		}

		if (exist < 0) {
			throw new RuntimeException("Specified sheet \""	+ sheetNameToGetIndex + "\" does not exist in file \"" + getFile().getAbsolutePath() + "\"");
		} else {
			return exist;
		}		
	}
	
	private static boolean isEmptyRow(Sheet sheet, int currentRow, int numOfColums){
		boolean isEmpty = false;		
		for (int i = 0; i < numOfColums; i++) {
			Cell cell = sheet.getCell(i, currentRow);			
			if(String.valueOf(cell.getContents()).trim().equals("") && (i == (numOfColums - 1))){
				isEmpty = true; 
			}
		}		
		return isEmpty;
	}
}