package com.fintech.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fintech.beans.DailyClientProcessingFile;
import com.fintech.beans.OutputFile;
import com.fintech.services.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping("/home")
	@ResponseBody
	public String showWelcomePage() {
		return "welcome to home";
	}
	
	@RequestMapping("/client")
	@ResponseBody
	public List<DailyClientProcessingFile> dailyClientFile(){
		//System.out.println(fileService.getDailyClientFile("push", 0));
		return  fileService.getDailyClientFile("push", 0);
		//List<DailyClientProcessingFile> dailyClientList=
		//return "daily-client";
	}
	
	@RequestMapping("/daily")
	@ResponseBody
	public List<OutputFile> dailyOPFile(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate to_date = LocalDate.parse("2023-06-14", formatter);
		LocalDate from_date = LocalDate.parse("2023-06-14", formatter);
		return fileService.getDailyOPFile(to_date, from_date, "UNCAPPED",null,0);
	}
}
