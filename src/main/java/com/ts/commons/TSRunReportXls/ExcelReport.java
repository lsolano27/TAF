package com.ts.commons.TSRunReportXls;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
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
import jxl.write.WritableFont.FontName;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelReport extends XLS
{	
	public ExcelReport(){}
	
	public ExcelReport(String xlsName) throws RowsExceededException, WriteException, IOException
	{
		super(xlsName);		
	}
	
	public ExcelReport inDirectory(String reportPath) throws RowsExceededException, WriteException, IOException
	{
		directoryName = reportPath;
		File file = new File(directoryName);
		
		if( ! file.exists())
		{
			file.mkdirs();
		}					
		
		return this;
	}	
	
	public ExcelReport buildReportHeader(String... colunmNames) throws Exception
	{		
		ArrayList<String> testCaseHeader = new ArrayList<String>();	
		
		for (String columName : colunmNames) 
		{
			testCaseHeader.add(columName);
		}
		
		setColumsHeader(testCaseHeader);
		createWorkbook();	
		buildReportCounts();	
		return this;
	}
	
	private void createGraph() throws Exception{
		new Graph(new File(directoryName + "/" + xlsName).getAbsoluteFile());
	}
	
	public ExcelReport buildReportCounts() throws Exception{		
		File file = new File(directoryName + "/" + xlsName);
		WritableWorkbook workbook = null;
		Workbook wb = null;
		WritableSheet sheet = null;
		
		try {
			if(file.exists())
			{
				wb = Workbook.getWorkbook(file);
				workbook = Workbook.createWorkbook(file, wb);	
				cleanBook(workbook);
				sheet =  workbook.createSheet("Results_Temp", 1);	
				sheet.addCell(new Label(0, 0, "TOTAL", headFormat()));
				sheet.addCell(new Label(1, 0, "SUCCESS", headFormat()));
				sheet.addCell(new Label(2, 0, "FAILURE", headFormat()));
				sheet.addCell(new Label(3, 0, "SKIP", headFormat()));
				sheet.addCell(new Label(0, 1, "", summayFormat()));
				sheet.addCell(new Label(1, 1, "", summayFormat()));
				sheet.addCell(new Label(2, 1, "", summayFormat()));
				sheet.addCell(new Label(3, 1, "", summayFormat()));				
			
			}
			else
			{
				System.err.println("File does not exist");
			}	
		} 
		catch (BiffException e)
		{
			e.printStackTrace();
		} 
		catch (RowsExceededException e)
		{
			e.printStackTrace();
		} 
		catch (WriteException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}finally{
			
			if(sheet != null){
				sheetAutoFitColumns(sheet);	
			}
			
			if(workbook != null)
			{				
				workbook.write();
				workbook.close();
			}
			
			if(wb != null)
			{
				wb.close();
			}
		}			
		return this;
	}
	
	private void cleanBook(WritableWorkbook workbook)
	{
		WritableSheet[] sheets = workbook.getSheets();		
		if(sheets.length > 1)
		{
			for (int i = 1; i < sheets.length; i++) 
			{
				workbook.removeSheet(i);
			}
		}
	}
	
	public ExcelReport createWorkbook() throws Exception{
		File file = new File(directoryName + "/" + xlsName);		 
		
		if( ! file.exists())
		{
			file = new File(file.getPath());
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			ws.setDrawingsDisabled(false);
			
			WritableWorkbook wb = Workbook.createWorkbook(file.getAbsoluteFile(), ws);
			createXls(wb, project, columsHeader);
		}
		return this;
	}
	
	public void addRow(String... columns) throws Exception
	{		
		File file = new File(directoryName + "/" + xlsName);
		WritableWorkbook workbook = null;
		Workbook wb = null;
		WritableSheet sheet = null;
		
		try {
			if(file.exists())
			{
				int colum = 0;
				wb = Workbook.getWorkbook(file);
				workbook = Workbook.createWorkbook(file, wb); 					
				sheet = workbook.getSheet("Report_TestCases");
				Cell testCaseCell = null;
				Cell parametersCell = null;
				String valorCeldaCurso=null;	
				
				for (int row = 2; row <= sheet.getRows(); row++) 
				{				
					testCaseCell= sheet.getCell(0,row);
					parametersCell = sheet.getCell(5,row);
					valorCeldaCurso= testCaseCell.getContents();
					String parametersToReport = "";
					
					if((columns[0].equals(valorCeldaCurso) && getParameterFormated(columns[5]).equals(parametersCell.getContents())) || valorCeldaCurso.equals(""))
					{
						for (int i = 0; i < columns.length; i++) 
						{
							if(i == 5 && parametersToReport.equals(""))
							{
								parametersToReport = getParameterFormated(columns[i]);
								columns[i] = parametersToReport;
							}
							
							if(columns[1].equals("SUCCESS"))
								{
									if(i == 4 || i == 5)
									{
										sheet.addCell(new Label(colum + i, row, columns[i]));		
									}
									else
									{
										sheet.addCell(new Label(colum + i, row, columns[i], passedFormat()));	
									}									
								}
								else if (columns[1].equals("SKIP"))
								{
									if(i < 4)
									{
										sheet.addCell(new Label(colum + i, row, columns[i], skippedFormat()));		
									}
									else if(i == 5)
									{
										sheet.addCell(new Label(colum + i, row, columns[i]));	
									}
								}
								else
								{
									if(i == 4)
									{					
										File newLink = new File(columns[i]);
										
										if(newLink.exists())
										{
											Formula f = new Formula(colum + i, row, "HYPERLINK(\"" + newLink.getAbsolutePath() +"\","+"\"" + newLink.getName() + "\")", linkFormat());
											sheet.addCell(f);
										}										
									}
									else if(i == 5)
									{
										sheet.addCell(new Label(colum + i, row, columns[i], smallFontStyle()));
									}
									else
									{
										sheet.addCell(new Label(colum + i, row, columns[i], failedFormat()));
									}
								}													
						}
						break;
					}
				}
			}
			else
			{
				System.err.println("File does not exist");
			}	
		} 
		catch (BiffException e) 
		{
			e.printStackTrace();
		} 
		catch (RowsExceededException e) 
		{
			e.printStackTrace();
		} 
		catch (WriteException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{		
			if(sheet != null)
			{
				sheetAutoFitColumns(sheet);	
			}
			
			if(workbook != null)
			{				
				workbook.write();
				workbook.close();
			}
			
			if(wb != null)
			{
				wb.close();
			}
		}
		
		createGraph();
	}	
	
	private String getParameterFormated(String textToFormat)
	{		
		if( ! textToFormat.equals(""))
		{
			String[] parameters = textToFormat.split("_param_");
			textToFormat = "(";
			
			for (int j = 0; j < parameters.length; j++) 
			{													
				if((j + 1) != parameters.length)
				{
					textToFormat += parameters[j] + ", ";
				}
				else
				{
					textToFormat += parameters[j] + ")";
				}
			}
		}
		else
		{
			textToFormat = "No parameters.";
		}
		
		return textToFormat;
	}
	
	private void createXls(WritableWorkbook workbook, String project, ArrayList<String> columsHeader) throws RowsExceededException, WriteException, IOException
	{
		WritableSheet sheet = workbook.createSheet("Report_TestCases",0); 		
		sheet.addCell(new Label(0, 0, project, projectNameFormat()));
		sheet.mergeCells(0, 0, columsHeader.size()-2, 0);		
		
		for (int i = 0; i < columsHeader.size(); i++) 
		{
			sheet.addCell(new Label(i, 2, columsHeader.get(i), headFormat()));
			
			if((i + 1) == columsHeader.size())
			{
				sheet.addCell(new Label(i, 2, columsHeader.get(i), parameterHeadStyle()));
			}
		}
		
		sheetAutoFitColumns(sheet);
		workbook.write();
        workbook.close();
	}
	
	private CellFormat parameterHeadStyle() throws WriteException 
	{
		return setFormat(Colour.GREEN, 12, Colour.WHITE, Alignment.JUSTIFY, UnderlineStyle.NO_UNDERLINE, WritableFont.ARIAL);
	}
	
	private CellFormat smallFontStyle() throws WriteException 
	{
		return setFormat(Colour.WHITE, 8, Colour.BLACK, Alignment.JUSTIFY, UnderlineStyle.NO_UNDERLINE, WritableFont.ARIAL);
	}
	
	private CellFormat projectNameFormat() throws WriteException 
	{
		return setFormat(Colour.BLUE_GREY, 16, Colour.WHITE, Alignment.CENTRE, UnderlineStyle.NO_UNDERLINE, WritableFont.ARIAL);
	}
	
	private CellFormat linkFormat() throws WriteException 
	{
		return setFormat(Colour.WHITE, 10, Colour.BLUE, Alignment.JUSTIFY, UnderlineStyle.SINGLE, WritableFont.ARIAL);
	}

	private static CellFormat headFormat() throws WriteException 
	{
		return setFormat(Colour.GREEN, 12, Colour.WHITE, Alignment.CENTRE, UnderlineStyle.NO_UNDERLINE, WritableFont.ARIAL);
	}
	
	private static CellFormat passedFormat() throws WriteException 
	{
		return setFormat(Colour.SKY_BLUE, 10, Colour.BLACK, Alignment.LEFT, UnderlineStyle.NO_UNDERLINE, WritableFont.ARIAL);
	}
	
	private static CellFormat failedFormat() throws WriteException 
	{		
		return setFormat(Colour.DARK_RED, 11, Colour.WHITE, Alignment.LEFT, UnderlineStyle.NO_UNDERLINE, WritableFont.ARIAL);
	}
	
	private static CellFormat skippedFormat() throws WriteException 
	{
		return setFormat(Colour.YELLOW, 10, Colour.BLACK, Alignment.LEFT, UnderlineStyle.NO_UNDERLINE, WritableFont.ARIAL);
	}
	
	private static CellFormat summayFormat() throws WriteException 
	{
		return setFormat(Colour.YELLOW2, 10, Colour.BLACK, Alignment.CENTRE, UnderlineStyle.NO_UNDERLINE, WritableFont.ARIAL);
	}

	private static CellFormat setFormat(Colour bgColor, int fontsize, Colour fontColor, Alignment alignment, UnderlineStyle underlineStyle, FontName fontName) throws WriteException 
	{
		WritableCellFormat format = new WritableCellFormat();
		WritableFont fontFormat = new WritableFont(fontName, fontsize, WritableFont.BOLD, false, underlineStyle, fontColor);
		format.setBackground(bgColor);
		format.setFont(fontFormat);
		format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		format.setAlignment(alignment);
		return format;		
	}
	
	private static void sheetAutoFitColumns(WritableSheet sheet) 
	{
		for (int i = 0; i < sheet.getColumns(); i++) 
		{
			Cell[] cells = sheet.getColumn(i);
			int longestStrLen = -1;

			if (cells.length == 0)
				continue;

			for (int j = 0; j < cells.length; j++) 
			{
				if (cells[j].getContents().length() > longestStrLen) 
				{
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