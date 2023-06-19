package com.fintech.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.beans.ConsolidatedOPFile;
import com.fintech.beans.DailyClientProcessingFile;
import com.fintech.beans.OutputFile;
import com.fintech.dao.OutputFileDAO;
import com.fintech.services.ExcelHelper;
import com.fintech.services.FileService;
import com.fintech.services.UserExcelExporter;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate  from_date= LocalDate.parse("2023-06-14", formatter);
	LocalDate to_date = LocalDate.parse("2023-06-15", formatter);
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/home")
	@ResponseBody
	public ResponseEntity<String> showWelcomePage() { //@RequestParam int id
		//return "welcome to home";
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}
	
	 @GetMapping("/download")
	  public ResponseEntity<InputStreamResource> getFile() {
	    String filename = "fileeee.csv";
	    InputStreamResource file = new InputStreamResource(fileService.load(to_date, from_date, "CAPPED",null,0));

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
	        .contentType(MediaType.parseMediaType("text/csv"))
	        .body(file);
	  }
	
	 @GetMapping("/dailyclient")
	 public ResponseEntity<List<DailyClientProcessingFile>> dailyClientFile() {
	     List<DailyClientProcessingFile> dailyClientList = fileService.getDailyClientFile("pull", 0);
	     return new ResponseEntity<>(dailyClientList, HttpStatus.OK);
	 }
	
	@GetMapping("/showop")
	public ResponseEntity<List<OutputFile>> dailyOPFile(){
		List<OutputFile> dailyoplist= fileService.getDailyOPFile(from_date, to_date, "CAPPED",null,0);
		System.out.println(dailyoplist.size());
		return new ResponseEntity<>(dailyoplist, HttpStatus.OK);
	}
	
//	@RequestMapping("/monthlyop")
//	@ResponseBody
//	public List<OutputFile> MonthlyOPFile(){
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate from_date = LocalDate.parse("2023-05-08", formatter);
//		LocalDate to_date = LocalDate.parse("2023-06-07", formatter);
//		List<OutputFile> monthlyoplist= fileService.getMonthlyOPFile(to_date, from_date, "CAPPED",null,0);
//		System.out.println(monthlyoplist.size());
//		return monthlyoplist;
//	}
//	
	
//	@GetMapping("/consolidated")
//	  public ResponseEntity<InputStreamResource> getConsolidatedOPFile() {
//	    String filename = "consolidated.csv";
//	    InputStreamResource file = new InputStreamResource(fileService.getConsolidatedOP(null, null));
//
//	    return ResponseEntity.ok()
//	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//	        .contentType(MediaType.parseMediaType("text/csv"))
//	        .body(file);
//	  }
	
	@GetMapping("/onlyop")
	@ResponseBody
	public List<ConsolidatedOPFile> getoponly() {
		return fileService.getConsolidatedOP(null, null);
	}
	
	@GetMapping("/downloadcons")
    public void downloadCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"data.csv\"");

        List<ConsolidatedOPFile> dataList = fileService.getConsolidatedOP(null, null);

        ExcelHelper.consolidatedOP(dataList, response.getWriter());
    }
}
