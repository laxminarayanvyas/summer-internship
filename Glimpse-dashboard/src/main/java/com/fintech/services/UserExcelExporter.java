package com.fintech.services;


import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fintech.beans.OutputFile;
 
public class UserExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<OutputFile> listUsers;
     
    public UserExcelExporter(List<OutputFile> listUsers) {
        this.listUsers = listUsers;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "DATE", style);      
        createCell(row, 1, "TIME(UTC)", style);       
        createCell(row, 2, "NO. DEALERS", style);    
        createCell(row, 3, "SIDE", style);
        createCell(row, 4, "ISIN", style);
        createCell(row, 5, "TICKER", style);
        createCell(row, 6, "MATURITY", style);
        createCell(row, 7, "COUPON (%)", style);
        createCell(row, 8, "SIZE (MM)", style);
        createCell(row, 9, "CCY", style);
        createCell(row, 10, "PRICE", style);
        createCell(row, 11, "MID PRICE", style);
        createCell(row, 12, "YIELD (%)", style);
        createCell(row, 13, "SPREAD (%)", style);
        createCell(row, 14, "SETTLEMENT", style);
        createCell(row, 15, "ON VENUE", style);
        createCell(row, 16, "VENUE", style);
        createCell(row, 17, "PROCESS TRADE", style);
        createCell(row, 18, "AUTOEX", style);
        createCell(row, 19, "PORTFOLIO TRADE", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (OutputFile file : listUsers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, file.getTrade_date(), style);
            createCell(row, columnCount++, file.getTrade_time(), style);
            createCell(row, columnCount++, file.getDealers_in_competition(), style);
            createCell(row, columnCount++, file.getSide(), style);
            createCell(row, columnCount++, file.getIsin(), style);
            createCell(row, columnCount++, file.getTicker(), style);
            createCell(row, columnCount++, file.getMaturity(), style);
            createCell(row, columnCount++, file.getCoupon_perc(), style);
            createCell(row, columnCount++, file.getSize_in_MM_actual(), style);
            createCell(row, columnCount++, file.getCurrency(), style);
            createCell(row, columnCount++, file.getPrice(), style);
            createCell(row, columnCount++, file.getMid_price(), style);
            createCell(row, columnCount++, file.getYield_perc(), style);
            createCell(row, columnCount++, file.getSpread(), style);
            createCell(row, columnCount++, file.getSettlement_date(), style);
            createCell(row, columnCount++, file.getOn_venue(), style);
            createCell(row, columnCount++, file.getVenue(), style);
            createCell(row, columnCount++, file.getProcess_trade(), style);
            createCell(row, columnCount++, file.getAuto_execution(), style);
            createCell(row, columnCount++, file.getPortfolio_trade(), style);
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}