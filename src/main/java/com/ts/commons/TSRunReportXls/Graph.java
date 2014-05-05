package com.ts.commons.TSRunReportXls;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

public class Graph {
	final int width = 640; 
	final int height = 480; 
	final float quality = 1;
	final String sheetNameTemp = "Results_Temp";
	final String sheetName = "Results";
	private static final int POSITION_X = 1;	 
	private static final int POSITION_Y = 5;	 
	private static final int CHART_WIDTH = 10 + POSITION_X; 	 
	private static final int CHART_HIGH = 23 + POSITION_Y; 
	
	public Graph(File excel) throws Exception
	{
		FileInputStream fis = new FileInputStream(excel.getAbsolutePath());	
		HSSFWorkbook my_workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = my_workbook.getSheet(sheetNameTemp);
		fis.close();
		setFormulas(my_workbook, sheet);
		DefaultPieDataset my_pie_chart_data = fillDataSet(sheet);		
		JFreeChart chart = drawChart(my_pie_chart_data);
		addChartToExcel(my_workbook, excel, chart);
	}
	
	private DefaultPieDataset fillDataSet(HSSFSheet sheet)
	{
		DefaultPieDataset my_pie_chart_data = new DefaultPieDataset();
		my_pie_chart_data.setValue(sheet.getRow(0).getCell(1).getStringCellValue() + " - " + (int)sheet.getRow(1).getCell(1).getNumericCellValue(), sheet.getRow(1).getCell(1).getNumericCellValue());
		my_pie_chart_data.setValue(sheet.getRow(0).getCell(2).getStringCellValue() + " - " + (int)sheet.getRow(1).getCell(2).getNumericCellValue(), sheet.getRow(1).getCell(2).getNumericCellValue());
		my_pie_chart_data.setValue(sheet.getRow(0).getCell(3).getStringCellValue() + " - " + (int)sheet.getRow(1).getCell(3).getNumericCellValue(), sheet.getRow(1).getCell(3).getNumericCellValue());
		return my_pie_chart_data;
	}
	
	private void setFormulas(HSSFWorkbook my_workbook, HSSFSheet sheet)
	{
		FormulaEvaluator evaluator = my_workbook.getCreationHelper().createFormulaEvaluator();
		sheet.createRow(1).createCell(0).setCellFormula("SUM(B2:D2)");
		sheet.getRow(1).createCell(1).setCellFormula("COUNTIF('Report_TestCases'!B:B,\"SUCCESS\")");
		sheet.getRow(1).createCell(2).setCellFormula("COUNTIF('Report_TestCases'!B:B,\"FAILURE\")");
		sheet.getRow(1).createCell(3).setCellFormula("COUNTIF('Report_TestCases'!B:B,\"SKIP\")");
		evaluator.evaluateInCell(sheet.getRow(1).getCell(1));
        evaluator.evaluateInCell(sheet.getRow(1).getCell(2));
        evaluator.evaluateInCell(sheet.getRow(1).getCell(3));
        evaluator.evaluateInCell(sheet.getRow(1).getCell(4));
	}	
	
	private JFreeChart drawChart(DefaultPieDataset my_pie_chart_data) throws Exception 
	{		
		JFreeChart myPieChart = ChartFactory.createPieChart3D("Excel Test Cases Result Graph", my_pie_chart_data, true, true, false);	
		PiePlot3D plot = (PiePlot3D ) myPieChart.getPlot(); 
		plot.setStartAngle(270);
		plot.setDirection(Rotation.ANTICLOCKWISE);
	    plot.setForegroundAlpha(0.60f);
	    Color blue = new Color(112, 127, 186);
		Color red =  new Color(240, 32, 32);
		Color yellow =  new Color(239, 246, 31);
		Color[] colors = {blue, red, yellow};
		PieRenderer renderer = new PieRenderer(colors);
        renderer.setColor(plot, my_pie_chart_data);		
		ByteArrayOutputStream chart_out = new ByteArrayOutputStream();
		ChartUtilities.writeChartAsJPEG(chart_out, quality, myPieChart, width, height);		 
		return myPieChart;
	}
	
	public void addChartToExcel(HSSFWorkbook wb, File excel, JFreeChart chart) throws Exception
	{        
		final BufferedImage buffer = chart.createBufferedImage(width, height);
		HSSFSheet sheet = wb.getSheet(sheetNameTemp);
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) POSITION_X, POSITION_Y, (short) CHART_WIDTH, CHART_HIGH);
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();	
		ByteArrayOutputStream img_bytes = new ByteArrayOutputStream();
		ImageIO.write(buffer, "png", img_bytes);
		img_bytes.flush();		
		int index = wb.addPicture(img_bytes.toByteArray(), Workbook.PICTURE_TYPE_PNG);				
		patriarch.createPicture(anchor, index);		
		
		if(wb.getSheet(sheetName) != null)
		{
			wb.removeSheetAt(wb.getSheetIndex(sheetName));
		}
		
		wb.setSheetName(wb.getSheetIndex(sheet), sheetName); 
		closeXls(excel, wb);
    }	
	
	private void closeXls(File excel, HSSFWorkbook wb) throws Exception
	{
		FileOutputStream out = new FileOutputStream(excel.getAbsolutePath());
		wb.write(out);
		out.close();
	}

	private static class PieRenderer 
    { 
        private Color[] color; 
        
        public PieRenderer(Color[] color) 
        { 
            this.color = color; 
        }        
        
        @SuppressWarnings({ "unchecked", "rawtypes" })
		public void setColor(PiePlot plot, DefaultPieDataset dataset) 
        { 
            List<Comparable> keys = dataset.getKeys(); 
            int aInt; 
            
            for (int i = 0; i < keys.size(); i++) 
            { 
                aInt = i % this.color.length; 
                plot.setSectionPaint(keys.get(i), this.color[aInt]); 
            } 
        } 
    }
}