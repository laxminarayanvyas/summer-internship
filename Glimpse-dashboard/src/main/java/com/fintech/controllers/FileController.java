package com.fintech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fintech.beans.DailyClientProcessingFile;
import com.fintech.services.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/home")
	@ResponseBody
	public String showWelcomePage() {
		return "welcome to home";
	}
	
	@RequestMapping("/client")
	@ResponseBody
	public List<DailyClientProcessingFile> dailyClientFile(){
		return fileService.getDailyClientFile("push", 0);
	}
}
