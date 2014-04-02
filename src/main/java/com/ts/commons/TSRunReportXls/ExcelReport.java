package com.ts.commons.TSRunReportXls;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelReport extends XLS{	
	
	public ExcelReport(String xlsName) throws RowsExceededException, WriteException, IOException{
		super(xlsName);		
	}
	
	public ExcelReport inDirectory(String reportPath) throws RowsExceededException, WriteException, IOException{
		directoryName = reportPath;
		File file = new File(directoryName);
		
		if( ! file.exists()){
			file.mkdirs();
		}					
		return this;
	}	
	
	public ExcelReport buildReportHeader(String... colunmNames) throws RowsExceededException, WriteException, IOException{		
		ArrayList<String> testCaseHeader = new ArrayList<String>();	
		
		for (String columName : colunmNames) {
			testCaseHeader.add(columName);
		}
		
		setColumsHeader(testCaseHeader);
		createWorkbook();	
		buildReportCounts();		
		return this;
	}
	
	public ExcelReport buildReportCounts() throws RowsExceededException, WriteException, IOException{		
		File file = new File(directoryName + "/" + xlsName);
		WritableWorkbook workbook = null;
		Workbook wb = null;
		WritableSheet sheet = null;
		
		try {
			if(file.exists()){
				wb = Workbook.getWorkbook(file.getAbsoluteFile());
				workbook = Workbook.createWorkbook(file, wb); 					
				sheet = workbook.getSheet(0);	
				
				sheet.addCell(new Formula(6, 2, "COUNTIF(B:C,\"Success\")", summayFormat()));
				sheet.addCell(new Formula(7, 2, "COUNTIF(B:C,\"FAILURE\")", summayFormat()));
				sheet.addCell(new Formula(8, 2, "COUNTIF(B:C,\"Skipped\")", summayFormat()));
				sheet.addCell(new Formula(5, 2, "SUM(G3:I3)", summayFormat()));
			}else{
				System.err.println("File does not exist");
			}	
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			if(sheet != null){
				sheetAutoFitColumns(sheet);	
			}
			
			if(workbook != null){				
				workbook.write();
				workbook.close();
			}
			
			if(wb != null){
				wb.close();
			}
		}			
		return this;
	}
	
	public ExcelReport createWorkbook() throws IOException, RowsExceededException, WriteException{
		File file = new File(directoryName + "/" + xlsName);
		
		if( ! file.exists()){
			file = new File(file.getPath());
			createXls(Workbook.createWorkbook(file.getAbsoluteFile()), project, columsHeader);
		}
		return this;
	}
	
	public void addRow(String... columns) throws IOException, WriteException{		 
		File file = new File(directoryName + "/" + xlsName);
		WritableWorkbook workbook = null;
		Workbook wb = null;
		WritableSheet sheet = null;
		
		try {
			if(file.exists()){
				int colum = 0;
				wb = Workbook.getWorkbook(file);
				workbook = Workbook.createWorkbook(file, wb); 					
				sheet = workbook.getSheet(0);
				Cell celdaCurso = null;
				String valorCeldaCurso=null;	
				
				for (int row = 2; row <= sheet.getRows(); row++) {					
					celdaCurso= sheet.getCell(0,row);
					valorCeldaCurso= celdaCurso.getContents();
					
					if(columns[0].equals(valorCeldaCurso) || valorCeldaCurso.equals("")){
						for (int i = 0; i < columns.length; i++) {							
							if(columns[1].equals("SUCCESS"))
								sheet.addCell(new Label(colum + i, row, columns[i], passedFormat()));
							
							else if (columns[1].equals("SKIPPED"))
								sheet.addCell(new Label(colum + i, row, columns[i], skippedFormat()));								
							else
								if(i == 4){
									WritableHyperlink link = new WritableHyperlink(colum + i, row, new File(columns[i]));
									sheet.addHyperlink(link);
								}else{
									sheet.addCell(new Label(colum + i, row, columns[i], failedFormat()));
								}								
						}
						break;							
					}
				}
			}else{
				System.err.println("File does not exist");
			}	
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			if(sheet != null){
				sheetAutoFitColumns(sheet);	
			}
			
			if(workbook != null){				
				workbook.write();
				workbook.close();
			}
			
			if(wb != null){
				wb.close();
			}
		}
	}	
	
	private void createXls(WritableWorkbook workbook, String project, ArrayList<String> columsHeader) throws RowsExceededException, WriteException, IOException{
		WritableSheet sheet = workbook.createSheet("Report of TestCases",0); 		
		sheet.addCell(new Label(0, 0, project, projectNameFormat()));
		sheet.mergeCells(0, 0, columsHeader.size()-1, 0);		
		
		for (int i = 0; i < columsHeader.size(); i++) {
			sheet.addCell(new Label(i, 1, columsHeader.get(i), headFormat()));
		}
		sheetAutoFitColumns(sheet);
		workbook.write();
        workbook.close();
	}
	
	private CellFormat projectNameFormat() throws WriteException {
		return setFormat(Colour.BLUE_GREY, 16, Colour.WHITE, Alignment.JUSTIFY);
	}

	private static CellFormat headFormat() throws WriteException {
		return setFormat(Colour.GREEN, 12, Colour.WHITE, Alignment.CENTRE);
	}
	
	private static CellFormat passedFormat() throws WriteException {
		return setFormat(Colour.SKY_BLUE, 10, Colour.BLACK, Alignment.LEFT);
	}
	
	private static CellFormat failedFormat() throws WriteException {
		return setFormat(Colour.RED, 11, Colour.WHITE, Alignment.LEFT);
	}
	
	private static CellFormat skippedFormat() throws WriteException {
		return setFormat(Colour.YELLOW, 10, Colour.BLACK, Alignment.LEFT);
	}
	
	private static CellFormat summayFormat() throws WriteException {
		return setFormat(Colour.YELLOW2, 10, Colour.BLACK, Alignment.CENTRE);
	}

	private static CellFormat setFormat(Colour bgColor, int fontsize, Colour fontColor, Alignment alignment) throws WriteException {
			WritableCellFormat format = new WritableCellFormat();
			WritableFont fontFormat = new WritableFont(WritableFont.ARIAL, fontsize, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, fontColor);
			format.setBackground(bgColor);
			format.setFont(fontFormat);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setAlignment(alignment);
			return format;		
	}
	
	private static void sheetAutoFitColumns(WritableSheet sheet) {
		for (int i = 0; i < sheet.getColumns(); i++) {
			Cell[] cells = sheet.getColumn(i);
			int longestStrLen = -1;

			if (cells.length == 0)
				continue;

			for (int j = 0; j < cells.length; j++) {
				if (cells[j].getContents().length() > longestStrLen) {
					String str = cells[j].getContents();
					if (str == null || str.isEmpty())
						continue;
					longestStrLen = str.trim().length();
				}
			}

			if (longestStrLen == -1)
				continue;

			if (longestStrLen > 255)
				longestStrLen = 255;

			CellView cv = sheet.getColumnView(i);
			cv.setSize((longestStrLen + 5) * 256 + 100);
			sheet.setColumnView(i, cv);
		}
	}
}