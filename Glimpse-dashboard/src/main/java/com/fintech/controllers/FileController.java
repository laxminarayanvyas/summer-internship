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

import com.fintech.beans.DailyClientProcessingFile;
import com.fintech.beans.OutputFile;
import com.fintech.dao.OutputFileDAO;
import com.fintech.services.FileService;
import com.fintech.services.UserExcelExporter;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate  from_date= LocalDate.parse("2023-06-05", formatter);
	LocalDate to_date = LocalDate.parse("2023-06-07", formatter);
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/home")
	@ResponseBody
	public String showWelcomePage() { //@RequestParam int id
		//System.out.println(map.get("startDate"));
		return "welcome to home";
	}
	
	 @GetMapping("/download")
	  public ResponseEntity<InputStreamResource> getFile() {
	    String filename = "fileeee.xlsx";
	    InputStreamResource file = new InputStreamResource(fileService.load(to_date, from_date, "CAPPED",null,0));

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
	        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
	        .body(file);
	  }
	
	@RequestMapping("/client")
	@ResponseBody
	public List<DailyClientProcessingFile> dailyClientFile(){
		//System.out.println(fileService.getDailyClientFile("push", 0));
		return  fileService.getDailyClientFile("push", 0);
		//List<DailyClientProcessingFile> dailyClientList=
		//return "daily-client";
	}
	
	@RequestMapping("/dailyop")
	@ResponseBody
	public List<OutputFile> dailyOPFile(){
		List<OutputFile> dailyoplist= fileService.getDailyOPFile(to_date, from_date, "CAPPED",null,0);
		System.out.println(dailyoplist.size());
		return dailyoplist;
	}
	
	@RequestMapping("/monthlyop")
	@ResponseBody
	public List<OutputFile> MonthlyOPFile(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate from_date = LocalDate.parse("2023-05-08", formatter);
		LocalDate to_date = LocalDate.parse("2023-06-07", formatter);
		List<OutputFile> monthlyoplist= fileService.getMonthlyOPFile(to_date, from_date, "CAPPED",null,0);
		System.out.println(monthlyoplist.size());
		return monthlyoplist;
	}
	
	@RequestMapping("/consolidated")
	@ResponseBody
	public List<OutputFile> consolidatedOPFile(){
		return null;
	}
}
