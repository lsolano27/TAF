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
import org.jfree.data.general.DefaultPieDataset;

public class Graph {
	final int width = 640; 
	final int height = 480; 
	final float quality = 1;
	final String sheetName = "Results";
	private static final int POSITION_X = 1;	 
	private static final int POSITION_Y = 5;	 
	private static final int CHART_WIDTH = 10 + POSITION_X; 	 
	private static final int CHART_HIGH = 23 + POSITION_Y; 
	
	public Graph(File excel) throws Exception
	{
		FileInputStream fis = new FileInputStream(excel.getAbsolutePath());	
		HSSFWorkbook my_workbook = new HSSFWorkbook(fis);
		fis.close();		
		HSSFSheet sheet = my_workbook.getSheet(sheetName);	
		
		deleteGraphIfExist(sheet);
		setFormulas(my_workbook, sheet);		
		DefaultPieDataset my_pie_chart_data = fillDataSet(sheet);		
		closeXls(excel, my_workbook);
		
		JFreeChart chart = drawChart(my_pie_chart_data);
		addChartToExcel(excel, chart);
	}
	
	private DefaultPieDataset fillDataSet(HSSFSheet sheet)
	{
		DefaultPieDataset my_pie_chart_data = new DefaultPieDataset();
		my_pie_chart_data.setValue(sheet.getRow(0).getCell(1).getStringCellValue(), sheet.getRow(1).getCell(1).getNumericCellValue());
		my_pie_chart_data.setValue(sheet.getRow(0).getCell(2).getStringCellValue(), sheet.getRow(1).getCell(2).getNumericCellValue());
		my_pie_chart_data.setValue(sheet.getRow(0).getCell(3).getStringCellValue(), sheet.getRow(1).getCell(3).getNumericCellValue());
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
	
	private void deleteGraphIfExist(HSSFSheet sheet)
	{
		HSSFPatriarch patriarch = sheet.getDrawingPatriarch();		

		if(patriarch != null)
		{
			sheet.getDrawingPatriarch().getChildren().clear();
		}	
	}
	 
	private JFreeChart drawChart(DefaultPieDataset my_pie_chart_data) throws Exception 
	{		
		JFreeChart myPieChart = ChartFactory.createPieChart("Excel Test Cases Result Graph", my_pie_chart_data, true, true, false);	
		PiePlot plot = (PiePlot) myPieChart.getPlot(); 
		Color[] colors = {new Color(102, 102, 255), new Color(204, 0, 0), Color.yellow};
		PieRenderer renderer = new PieRenderer(colors);
        renderer.setColor(plot, my_pie_chart_data);		
		ByteArrayOutputStream chart_out = new ByteArrayOutputStream();
		ChartUtilities.writeChartAsJPEG(chart_out, quality, myPieChart, width, height);		 
		return myPieChart;
	}
	
	public void addChartToExcel(File excel, JFreeChart chart) throws Exception
	{        
		final BufferedImage buffer = chart.createBufferedImage(width, height);
		FileInputStream in = new FileInputStream(excel.getPath());
		HSSFWorkbook wb = new HSSFWorkbook(in, true);
		in.close();
		ByteArrayOutputStream img_bytes = new ByteArrayOutputStream();
		ImageIO.write(buffer, "png", img_bytes);
		img_bytes.flush();
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) POSITION_X, POSITION_Y, (short) CHART_WIDTH, CHART_HIGH);
		int index = wb.addPicture(img_bytes.toByteArray(), Workbook.PICTURE_TYPE_PNG);
		HSSFSheet sheet = wb.getSheet(sheetName);
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		patriarch.createPicture(anchor, index);
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