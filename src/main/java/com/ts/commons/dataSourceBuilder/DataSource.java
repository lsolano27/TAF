package com.ts.commons.dataSourceBuilder;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataSource {	
	
	public static Object[][] getData(String inputFile, int sheetNum, int rowToBeginRead) {	
		Object[][] data = null;
	    Workbook book = null;	    
	    inputFile = depureFileName(inputFile);
		String fileLocation = getFileLocation(inputFile);
		
		try {			
			if(new File(fileLocation).exists()){				
				book = Workbook.getWorkbook(new File(fileLocation));
				Sheet sheet = book.getSheet(sheetNum);
				int numberOfColumns = sheet.getColumns();
				int rows = getRows(sheet);
				data = new Object[rows - 1][numberOfColumns];
				
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
			}else{
				System.err.println("Specified file \"" + fileLocation + "\" does not exist");
			}	       
	    }catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
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
	
	private static String depureFileName(String inputFile){
		if(inputFile.contains(".xls")){
			return inputFile;
		}else{
			return inputFile + ".xls";
		}
	}
	
	private static String getFileLocation(String inputFile){
		String diskLocation = DataSource.class.getResource("").getPath();
		int reference = diskLocation.lastIndexOf("/target");
		diskLocation = diskLocation.substring(0, reference);
		diskLocation = diskLocation+"/src/main/resources/" + inputFile;		
		return diskLocation;
	}
}
