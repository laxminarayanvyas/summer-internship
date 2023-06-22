package com.fintech.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import com.fintech.services.ExcelHelper;
import com.fintech.services.FileService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Autowired
	private FileService fileService;

	@GetMapping("/home")
	@ResponseBody
	public ResponseEntity<String> showWelcomePage() { // @RequestParam int id
		// return "welcome to home";
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}

	/* daily client processing file */
	@GetMapping("/dailyclient")
	public ResponseEntity<List<DailyClientProcessingFile>> dailyClientFile() {
//		LocalDate from_date, to_date;
//		to_date = LocalDate.now();
//		from_date = to_date.minusDays(1);

//		LocalDate to_date = LocalDate.now();
//		LocalTime time = LocalTime.now();
//		LocalDateTime to_dateTime = LocalDateTime.of(to_date, time);
//
//		LocalDate from_date = to_date.minusDays(1);
//		// time = LocalTime.of(9, 5);
//		time = LocalTime.now();
//		LocalDateTime from_dateTime = LocalDateTime.of(from_date, time);

		// for testing only
		/*
		 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
		 * LocalDate from_date= LocalDate.parse("2023-03-23", formatter); LocalDate
		 * to_date = LocalDate.parse("2023-03-28", formatter);
		 */

		/*
		 * entering date manually LocalDateTime currentDateTime = LocalDateTime.now();
		 * LocalDateTime previousDateTime = currentDateTime.minusDays(2);
		 */

		LocalDate to_date = LocalDate.now(), from_date;
		LocalTime time = LocalTime.now();
		LocalDateTime to_dateTime = LocalDateTime.of(to_date, time), from_dateTime;
		from_date = to_date.minusDays(1);
		from_dateTime = LocalDateTime.of(from_date, time);

		System.err.println("daily  client : " + from_dateTime + " " + to_dateTime);
		List<DailyClientProcessingFile> dailyClientList = fileService.getDailyClientFile("pull", 0, from_dateTime,
				to_dateTime);
		// List<DailyClientProcessingFile> dailyClientList =
		// fileService.getDailyClientFile("pull", 0, previousDateTime,currentDateTime);
		// List<DailyClientProcessingFile> dailyClientList =
		// fileService.getDailyClientFile('pull',0,'2023-06-19
		// 09:30:00.000000','2023-06-21 07:30:00.000000');
		return new ResponseEntity<>(dailyClientList, HttpStatus.OK);
	}

	/* when view button clicked for daily op file */
	@GetMapping("/showdailyop")
	public ResponseEntity<List<OutputFile>> dailyOPFile() {
		LocalDate from_date, to_date;
		to_date = LocalDate.now();
		from_date = to_date.minusDays(1);

		System.out.println("daily output: " + from_date + " " + to_date);
		List<OutputFile> dailyoplist = fileService.getDailyOPFile(from_date, to_date, null, "CAPPED", 0);
		System.out.println(dailyoplist.size());
		return new ResponseEntity<>(dailyoplist, HttpStatus.OK);
	}

	/* when view button clicked for monthly op file */
	@GetMapping("/showmonthlyop")
	public ResponseEntity<List<OutputFile>> monthlyOPFile() {
		LocalDate from_date, to_date;

		to_date = LocalDate.now();
		from_date = to_date.minusMonths(1);

		System.out.println("monthly output: " + from_date + " " + to_date);
		List<OutputFile> monthlyOPList = fileService.getMonthlyOPFile(from_date, to_date, null, "CAPPED", 0);
		System.out.println(monthlyOPList.size());
		return new ResponseEntity<>(monthlyOPList, HttpStatus.OK);
	}

	
	//controller to download consolidated output file
	@GetMapping("/downloadcons")
	public void downloadCSV(HttpServletResponse response, @RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate) throws IOException {
		System.out.println(startDate + " " + endDate);
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"ConsolidatedData.csv\"");

		List<ConsolidatedOPFile> dataList = fileService.getConsolidatedOP(null, null);

		ExcelHelper.consolidatedOP(dataList, response.getWriter());
	}

	/* download daily output and monthly output file */
	@GetMapping("/download")
	public ResponseEntity<InputStreamResource> getFile(@RequestParam(name = "id") int id) {

		LocalDate from_date, to_date;
		if (id == 1) {									//To download daily op file data we taking date
			to_date = LocalDate.now();
			from_date = to_date.minusDays(1);
		} else {
			to_date = LocalDate.now();
			from_date = to_date.minusMonths(1);
		}
		System.out.println(from_date + " " + to_date + " " + id);
		String filename = "GlimpseData" + from_date + to_date + ".csv";
		ByteArrayInputStream inputStream = (fileService.load(from_date, to_date, null, "CAPPED", 0));

		if (inputStream == null) {
			String message = "No file available for download.";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN)
					.body(new InputStreamResource(new ByteArrayInputStream(message.getBytes())));
		}

		InputStreamResource file = new InputStreamResource(inputStream);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("text/csv")).body(file);

		/*
		 * This is first code i implemented to download file InputStreamResource file =
		 * new InputStreamResource(fileService.load(from_date, to_date, null, "CAPPED",
		 * 0)); return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
		 * "attachment; filename=" + filename)
		 * .contentType(MediaType.parseMediaType("text/csv")).body(file);
		 */
	}

	//controller to know statistics of both Output files
	@GetMapping("dailyopstat")
	public ResponseEntity<DailyClientProcessingFile> getDailyOPStat(@RequestParam(name = "id") int id) {
		LocalDate to_date = LocalDate.now(), from_date;
		LocalTime time = LocalTime.now();
		LocalDateTime to_dateTime = LocalDateTime.of(to_date, time), from_dateTime;
		DailyClientProcessingFile opStatList;

		/*
		 * Date we enter manually
		 * LocalDateTime currentDateTime = LocalDateTime.now(); LocalDateTime
		 * previousDateTime = currentDateTime.minusDays(2); LocalDateTime
		 * previousMonthDateTime = currentDateTime.minusMonths(1);
		 */

		// Get the previous date and time

		if (id == 1) {  // for daily op file
			from_date = to_date.minusDays(1);
			from_dateTime = LocalDateTime.of(from_date, time);

			opStatList = fileService.getDailyOPStat("push", 0, from_dateTime, to_dateTime, id);
			System.out.println(from_dateTime + " " + to_dateTime);
			if (opStatList == null)
				return null;
			else
				return new ResponseEntity<>(opStatList, HttpStatus.OK);
		} else { 										//for monthly op file

			from_date = to_date.minusMonths(1);
			from_dateTime = LocalDateTime.of(from_date, time);

			System.out.println(from_dateTime + " " + to_dateTime);
			opStatList = fileService.getMonthlyOPStat("push", 0, from_dateTime, to_dateTime, id);
			return new ResponseEntity<>(opStatList, HttpStatus.OK);
		}
	}
}
