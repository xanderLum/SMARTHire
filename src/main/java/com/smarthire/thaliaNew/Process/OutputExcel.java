/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Nathalia
 */
public class OutputExcel {
    
	private String username;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//String outputFileDestinationPath = "C:\\Users\\Nathalia\\Desktop\\SMARTHire\\output";
	String outputFileDestinationPath = "D:\\THESIS TO GO MAN\\Final Integration\\SMARTHire\\output";
    HSSFWorkbook workbook;
    HSSFFont font;
    HSSFCellStyle cellStyle;
    HSSFCellStyle titleCellStyle;
    HSSFFont titleFont;
    HSSFCellStyle subTitleCellStyle;
    public OutputExcel()
    {
        workbook = new HSSFWorkbook();
        cellStyle = workbook.createCellStyle();
        font = workbook.createFont();
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setBorderTop((short) 1);
        cellStyle.setBorderBottom((short) 1);
        cellStyle.setBorderLeft((short) 1);
        cellStyle.setBorderRight((short) 1);
        
        
        cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short)10);
        cellStyle.setFont(font);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
        titleCellStyle = workbook.createCellStyle();
        titleFont = workbook.createFont();
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleCellStyle.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        titleFont.setColor(HSSFColor.WHITE.index);
        titleFont.setFontHeightInPoints((short)12);
        titleCellStyle.setFont(titleFont);
        titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        titleCellStyle.setWrapText(true);
        titleCellStyle.setBorderTop((short) 1);
        titleCellStyle.setBorderBottom((short) 1);
        titleCellStyle.setBorderLeft((short) 1);
        titleCellStyle.setBorderRight((short) 1);
        
        subTitleCellStyle = workbook.createCellStyle();
        subTitleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        subTitleCellStyle.setWrapText(true);
        subTitleCellStyle.setBorderTop((short) 1);
        subTitleCellStyle.setBorderBottom((short) 1);
        subTitleCellStyle.setBorderLeft((short) 1);
        subTitleCellStyle.setBorderRight((short) 1);
        subTitleCellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        subTitleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    }
    
    public void saveOutput(String nameOfSheet, Map<String, Object[]> data , int maxCellNum)
    {
        HSSFSheet sheet = workbook.createSheet(nameOfSheet);
        
        Set<String> keyset = data.keySet();

        
        for (String key : keyset) {
          //Row row = sheet.createRow(rownum++);
          
          Row row = sheet.createRow(Integer.parseInt(key));
          
          row.setHeight((short)1000);
          
          Object [] objArr = data.get(key);
          int cellnum = 1;
          for (Object obj : objArr) {
            Cell cell = row.createCell(cellnum++);
            
            if(key.equalsIgnoreCase("1"))
            {
                cell.setCellStyle(titleCellStyle);
            }
            else{
                cell.setCellStyle(cellStyle);
            }
            
            
            
             //----------------------
             
             if(obj instanceof Date) 
                cell.setCellValue((Date)obj);
             else if(obj instanceof Boolean)
                cell.setCellValue((Boolean)obj);
             else if(obj instanceof String)
                cell.setCellValue((String)obj);
             else if(obj instanceof Double)
                cell.setCellValue((Double)obj);
             
           }
          if(cellnum==2)
          {
              Cell cell = row.getCell(1);
              cell.setCellStyle(subTitleCellStyle);
              for(int i = 2; i<maxCellNum; i++)
              {
                  cell = row.createCell(i);
                  cell.setCellStyle(subTitleCellStyle);
              }
              
              Region region = new Region();
              region.setColumnFrom((short)1);
              region.setColumnTo((short)(maxCellNum-1));
              region.setRowFrom((short)row.getRowNum());
              region.setRowTo((short)row.getRowNum());
              sheet.addMergedRegion(region);
          }
          else if(maxCellNum<cellnum)
          {
              maxCellNum=cellnum;
          }          
        }

        for(int i=1; i<maxCellNum; i++)
        {
            //sheet.setColumnWidth(i, 20000);
            sheet.autoSizeColumn(i);
        }
        sheet.setActive(false);
        sheet.setGridsPrinted(true);
       //sheet.autoSizeColumn(2);
        try {
          FileOutputStream out = new FileOutputStream(new File(outputFileDestinationPath+"\\output"+this.username+".xls"));
           workbook.write(out);
         out.close();
         System.out.println("Excel written successfully..");

         } catch (FileNotFoundException e) {
           e.printStackTrace();
         } catch (IOException e) {
          e.printStackTrace();
    }
    }
}
    
