package com.ts.commons;

import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataSourceXls extends DataSource{
	
	
	
	public DataSourceXls(String path) {
		super(path);
	}
	
	public Object[][] getData() throws BiffException, IOException {
		return getData( 0, 2);
	}
	
	public Object[][] getData(int sheetNum, int rowToBeginRead) throws BiffException, IOException {	
		Object[][] data = null;
	    Workbook book = null;	    
						
		book = Workbook.getWorkbook(getFile());
		Sheet sheet = book.getSheet(sheetNum);
		int numberOfColumns = sheet.getColumns();
		int rows = getRows(sheet);
		data = new Object[rows - (rowToBeginRead - 1)][numberOfColumns];
		
		 for (int row = rowToBeginRead; row < sheet.getRows(); row++) {	  	    	  
	    	  for (int colum = 0; colum < sheet.getColumns(); colum++) {	    		  
	    		  Cell cell = sheet.getCell(colum, row);			    		  
	    		  if(isEmptyRow(sheet, row, sheet.getColumns())){
	    			  break;			    			 
	    		  }else{			    			 
	    			  data[row - (rowToBeginRead)][colum] = String.valueOf(cell.getContents());			    			  			    			  
	    		  }		    		  
	    	  }	    	    	  
	      }					 
   
		
		if(book != null){
			book.close();
		}		
    	return data;
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
	
	private static int getRows(Sheet sheet){
		int cont = 0;
		for (int j = 1; j < sheet.getRows(); j++) {	  	    	  
    	  for (int i = 0; i < sheet.getColumns(); i++) {	    		  
    		  Cell cell = sheet.getCell(i, j);	    		  
    		  if( ! String.valueOf(cell.getContents()).equals("")){
    			  cont ++; 
    			  break;
    		  }
    	  }	     	  	    	  
	    }			
		return cont;
	}

}
