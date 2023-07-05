package com.fintech.controllers;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;
import java.util.zip.GZIPOutputStream;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.fintech.beans.ConsolidatedOPFile;
import com.fintech.beans.DailyClientProcessingFile;
import com.fintech.beans.OutputFile;
import com.fintech.services.ExcelHelper;
import com.fintech.services.FileService;

@RestController
@CrossOrigin(origins = { "http://192.168.137.1:8081", "http://192.168.0.141:8081", "http://192.168.137.1:10001","http://localhost:4200" })
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
	public ResponseEntity<List<DailyClientProcessingFile>> dailyClientFile(
			@RequestParam(name = "searchDate") String date) {
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

		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate to_date = LocalDate.parse(date, formatter);
		LocalDate from_date;
		LocalTime time = LocalTime.now();
		LocalDateTime to_dateTime = LocalDateTime.of(to_date, time), from_dateTime;
		from_date = to_date.minusDays(1);
		from_dateTime = LocalDateTime.of(from_date, time);

		System.err.println("hi it is daily  client : " + from_dateTime + " " + to_dateTime);
		List<DailyClientProcessingFile> dailyClientList = fileService.getDailyClientFile("pull", 0, from_dateTime,
				to_dateTime);
		System.out.println(dailyClientList);
		// List<DailyClientProcessingFile> dailyClientList =
		// fileService.getDailyClientFile("pull", 0, previousDateTime,currentDateTime);
		// List<DailyClientProcessingFile> dailyClientList =
		// fileService.getDailyClientFile('pull',0,'2023-06-19
		// 09:30:00.000000','2023-06-21 07:30:00.000000');
		return new ResponseEntity<>(dailyClientList, HttpStatus.OK);
	}

	/* when view button clicked for daily op file */
	@GetMapping("/showdailyop")
	public ResponseEntity<List<OutputFile>> dailyOPFile(@RequestParam(name = "searchDate") String date) {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate to_date = LocalDate.parse(date, formatter);
		LocalDate from_date;
		to_date = LocalDate.now();
		from_date = to_date.minusDays(1);

		System.out.println("daily output: " + from_date + " " + to_date);
		List<OutputFile> dailyoplist = fileService.getDailyOPFile(from_date, to_date, null, "CAPPED", 0);
		System.out.println(dailyoplist.size());
		return new ResponseEntity<>(dailyoplist, HttpStatus.OK);
	}

	/* when view button clicked for monthly op file */
	@GetMapping("/showmonthlyop")
	public ResponseEntity<List<OutputFile>> monthlyOPFile(@RequestParam(name = "searchDate") String date) {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate to_date = LocalDate.parse(date, formatter);
		LocalDate from_date;

		to_date = LocalDate.now();
		from_date = to_date.minusMonths(1);

		System.out.println("monthly output: " + from_date + " " + to_date);
		List<OutputFile> monthlyOPList = fileService.getMonthlyOPFile(from_date, to_date, null, "CAPPED", 0);
		System.out.println(monthlyOPList.size());
		return new ResponseEntity<>(monthlyOPList, HttpStatus.OK);
	}

	// controller to download consolidated output file

	@GetMapping("/downloadcons")
	public void downloadCSV(HttpServletResponse response, @RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate, @RequestParam(name = "type") Boolean type)
			throws IOException {
		System.out.println(startDate + " " + endDate);
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"ConsolidatedData.csv\"");

		// System.out.println(type.getClass().getName());
		List<ConsolidatedOPFile> dataList;
		if (type)
			dataList = fileService.getConsolidatedOP(startDate, endDate, "CAPPED");
		else
			dataList = fileService.getConsolidatedOP(startDate, endDate, "UNCAPPED");

		// Create a Callable to generate the CSV data
		Callable<Stream<String>> csvDataCallable = () -> ExcelHelper.consolidatedOP(dataList);

		// Execute the CSV generation task asynchronously
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<Stream<String>> csvDataFuture = executorService.submit(csvDataCallable);

		try (PrintWriter writer = response.getWriter()) {
			// Retrieve the CSV data from the Future
			try {
				Stream<String> csvData = csvDataFuture.get();

				// Write the CSV data to the response
				csvData.forEach(writer::println);
			} catch (InterruptedException | ExecutionException e) {
				// Handle the exception
				e.printStackTrace();
			}
		}

		// Shutdown the executor service
		executorService.shutdown();
	}

	/*
	 * @GetMapping("/downloadcons") public void downloadCSV(HttpServletResponse
	 * response, @RequestParam(name = "startDate") String startDate,
	 * 
	 * @RequestParam(name = "endDate") String endDate) throws IOException {
	 * System.out.println(startDate + " " + endDate);
	 * response.setContentType("application/zip");
	 * response.setHeader("Content-Disposition",
	 * "attachment; filename=\"ConsolidatedData.zip\"");
	 * 
	 * List<ConsolidatedOPFile> dataList = fileService.getConsolidatedOP(startDate,
	 * endDate);
	 * 
	 * try (ZipOutputStream zipOut = new
	 * ZipOutputStream(response.getOutputStream())) { // Create a Callable to
	 * generate the CSV data Callable<byte[]> csvDataCallable = () ->
	 * ExcelHelper.generateCSVData(dataList);
	 * 
	 * // Execute the CSV generation task asynchronously ExecutorService
	 * executorService = Executors.newSingleThreadExecutor(); Future<byte[]>
	 * csvDataFuture = executorService.submit(csvDataCallable);
	 * 
	 * try { // Retrieve the CSV data from the Future byte[] csvData =
	 * csvDataFuture.get();
	 * 
	 * // Add the CSV file to the ZIP archive ZipEntry zipEntry = new
	 * ZipEntry("ConsolidatedData.csv"); zipOut.putNextEntry(zipEntry);
	 * zipOut.write(csvData); zipOut.closeEntry(); } catch (InterruptedException |
	 * ExecutionException e) { // Handle the exception e.printStackTrace(); }
	 * finally { // Shutdown the executor service executorService.shutdown(); } } }
	 */

	/*
	 * @GetMapping("/downloadcons") public void downloadCSV(HttpServletResponse
	 * response, @RequestParam(name = "startDate") String startDate,
	 * 
	 * @RequestParam(name = "endDate") String endDate) throws IOException {
	 * System.out.println(startDate + " " + endDate);
	 * response.setContentType("text/csv");
	 * response.setHeader("Content-Disposition",
	 * "attachment; filename=\"ConsolidatedData.csv\"");
	 * 
	 * List<ConsolidatedOPFile> dataList =
	 * fileService.getConsolidatedOP(startDate,endDate);
	 * 
	 * Stream<String> csvData = ExcelHelper.consolidatedOP(dataList);
	 * 
	 * 
	 * try (PrintWriter writer = response.getWriter()) {
	 * csvData.forEach(writer::println); }
	 * 
	 * 
	 * }
	 */

	/*
	 * public ResponseEntity<Resource> downloadSingleReport() { File dlFile = new
	 * File("some_path"); if (!dlFile.exists()) { return
	 * ResponseEntity.notFound().build(); }
	 * 
	 * try { try (InputStream stream = new FileInputStream(dlFile)) {
	 * InputStreamResource streamResource = new InputStreamResource(stream); return
	 * ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
	 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
	 * dlFile.getName() + "\"") .body(streamResource); }
	 * 
	 * } catch (IOException e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
	 */

	/* download daily output and monthly output file */
	@GetMapping("/download")
	public void getFile(HttpServletResponse response, @RequestParam(name = "id") int id,
			@RequestParam(name = "searchDate") String date) throws IOException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"OPfile.csv\"");

		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate to_date = LocalDate.parse(date, formatter);
		LocalDate from_date;
		if (id == 1) { // To download daily op file data we take date
			from_date = to_date.minusDays(1);
		} else {
			from_date = to_date.minusMonths(1);
		}
		System.out.println(from_date + " " + to_date + " " + id);
		String filename = "GlimpseData" + from_date + to_date + ".csv";

		// Create a Callable to load the CSV data
		Callable<Stream<String>> csvDataCallable = () -> fileService.load(from_date, to_date, null, "CAPPED", 0);

		// Execute the CSV loading task asynchronously
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<Stream<String>> csvDataFuture = executorService.submit(csvDataCallable);

		try (PrintWriter writer = response.getWriter()) {
			// Retrieve the CSV data from the Future
			try {
				Stream<String> csvData = csvDataFuture.get();

				// Write the CSV data to the response
				csvData.forEach(writer::println);
			} catch (InterruptedException | ExecutionException e) {
				// Handle the exception
				e.printStackTrace();
			}
		}

		// Shutdown the executor service
		executorService.shutdown();
	}

	/*
	 * @GetMapping("/download") public void getFile(HttpServletResponse
	 * response,@RequestParam(name = "id") int id)throws IOException {
	 * response.setContentType("text/csv");
	 * response.setHeader("Content-Disposition",
	 * "attachment; filename=\"OPfile.csv\"");
	 * 
	 * LocalDate from_date, to_date; if (id == 1) { // To download daily op file
	 * data we taking date to_date = LocalDate.now(); from_date =
	 * to_date.minusDays(1); } else { to_date = LocalDate.now(); from_date =
	 * to_date.minusMonths(1); } System.out.println(from_date + " " + to_date + " "
	 * + id); String filename = "GlimpseData" + from_date + to_date + ".csv";
	 * Stream<String> csvData = (fileService.load(from_date, to_date, null,
	 * "CAPPED", 0));
	 * 
	 * 
	 * if (inputStream == null) { String message =
	 * "No file available for download."; return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN)
	 * .body(new InputStreamResource(new ByteArrayInputStream(message.getBytes())));
	 * }
	 * 
	 * 
	 * 
	 * 
	 * try (PrintWriter writer = response.getWriter()) {
	 * csvData.forEach(writer::println); }
	 * 
	 * 
	 * }
	 */

	// controller to know statistics of both Output files
	@GetMapping("opstat")
	public ResponseEntity<List<DailyClientProcessingFile>> getDailyOPStat(
			@RequestParam(name = "searchDate") String date) {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate to_date = LocalDate.parse(date, formatter);
		LocalDate from_date;
		LocalTime time = LocalTime.now();
		LocalDateTime to_dateTime = LocalDateTime.of(to_date, time), from_dateTime;

		from_date = to_date.minusDays(1);
		from_dateTime = LocalDateTime.of(from_date, time);

		System.out.println("opstat: " + from_dateTime + " " + to_dateTime);

		return new ResponseEntity<>(fileService.getDailyMonthlyOPFilesStat("push", 0, from_dateTime, to_dateTime),
				HttpStatus.OK);
	}

	 private final ExecutorService executorService = Executors.newFixedThreadPool(10);

	    // file download from SFTP
	    @GetMapping("/{clientName}/{filePath}")
	    public ResponseEntity<byte[]> downloadFile1(@PathVariable String clientName, @PathVariable String filePath,
	                                               @RequestParam(name = "updatedDate") String date) {

	        String finalString = filePath.replaceFirst("\\.([^.]*)$", "_" + date + ".$1");
	        System.out.println(finalString);

	        // Create a task to download the file asynchronously
	        Callable<byte[]> downloadTask = () -> fileService.downloadFile(clientName, finalString);

	        try {
	            // Submit the download task to the executor service
	            Future<byte[]> future = executorService.submit(downloadTask);

	            // Wait for the download to complete
	            byte[] fileBytes = future.get();

	            if (fileBytes != null) {
	                return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(fileBytes);
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        } catch (ExecutionException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	@GetMapping("/{clientName}/{filePath}/sdsdsd")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String clientName, @PathVariable String filePath,
			@RequestParam(name = "updatedDate") String date) {

		/*
		 * // Get the file extension from the first string String fileExtension =
		 * firstString.substring(firstString.lastIndexOf('.') + 1); // Remove the file
		 * extension from the first string String fileNameWithoutExtension =
		 * firstString.substring(0, firstString.lastIndexOf('.')); // Concatenate the
		 * strings with an underscore String finalString = fileNameWithoutExtension +
		 * "_" + secondString + "." + fileExtension;
		 */
		String finalString = filePath.replaceFirst("\\.([^.]*)$", "_" + date + ".$1");
		System.out.println(finalString);
		byte[] fileBytes = fileService.downloadFile(clientName, finalString);
		if (fileBytes != null) {
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(fileBytes);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
