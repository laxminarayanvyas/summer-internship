package com.fintech.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fintech.beans.OutputFile;

public class ExcelHelper {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "DATE", "TIME(UTC)", "NO. DEALERS", "SIDE",
		  "ISIN", "TICKER", "MATURITY", "COUPON (%)",
		  "SIZE", "CCY", "PRICE", "MID PRICE",
		  "YIELD (%)", "SPREAD (%)", "SETTLEMENT", "ON VENUE",
		  "VENUE", "PROCESS TRADE", "AUTOEX", "PORTFOLIO TRADE"};
  static String SHEET = "Outputfile";

  public static ByteArrayInputStream tutorialsToExcel(List<OutputFile> tutorials) {

    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
      Sheet sheet = workbook.createSheet(SHEET);

      // Header
      Row headerRow = sheet.createRow(0);

      for (int col = 0; col < HEADERs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(HEADERs[col]);
      }

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Change the date format as per your requirement
      
      int rowIdx = 1;
      for (OutputFile file : tutorials) {
        Row row = sheet.createRow(rowIdx++);
        
        
        row.createCell(0).setCellValue( file.getTrade_date().format(formatter)); //==null?"":file.getTrade_date()  file.getTrade_date()
        row.createCell(1).setCellValue(file.getTrade_time()); //==null?0:file.getTrade_time()
        row.createCell(2).setCellValue(file.getDealers_in_competition()==null?0:file.getDealers_in_competition());
        row.createCell(3).setCellValue(file.getSide()==null?"":file.getSide());
        row.createCell(4).setCellValue(file.getIsin()==null?"":file.getIsin());
        row.createCell(5).setCellValue(file.getTicker()==null?"":file.getTicker());
        row.createCell(6).setCellValue(file.getMaturity().format(formatter)); //==null?0:file.getMaturity()
        row.createCell(7).setCellValue(file.getCoupon_perc()==null?0:file.getCoupon_perc()); //getCoupon_perc()
        row.createCell(8).setCellValue(file.getSize_in_MM_actual()==null?0:file.getSize_in_MM_actual());
        row.createCell(9).setCellValue(file.getCurrency()==null?"":file.getCurrency());
        row.createCell(10).setCellValue(file.getPrice()==null?0:file.getPrice());
        row.createCell(11).setCellValue(file.getMid_price()==null?0:file.getMid_price());
        row.createCell(12).setCellValue(file.getYield_perc()==null?0:file.getYield_perc());
        row.createCell(13).setCellValue(file.getSpread()==null?0:file.getSpread());
        row.createCell(14).setCellValue(file.getSettlement_date().format(formatter)); //==null?0:file.getSettlement_date()
        row.createCell(15).setCellValue(file.getOn_venue()==null?"":file.getOn_venue());
        row.createCell(16).setCellValue(file.getVenue()==null?"":file.getVenue());
        row.createCell(17).setCellValue(file.getProcess_trade()==null?"":file.getProcess_trade());
        row.createCell(18).setCellValue(file.getAuto_execution()==null?"":file.getAuto_execution());
        row.createCell(19).setCellValue(file.getAuto_execution()==null?"":file.getAuto_execution());
      }

      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    }
  }
}
