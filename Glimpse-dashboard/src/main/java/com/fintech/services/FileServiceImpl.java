package com.fintech.services;

import java.io.ByteArrayInputStream;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import com.fintech.beans.ConsolidatedOPFile;
import com.fintech.beans.DailyClientProcessingFile;
import com.fintech.beans.OutputFile;
import com.fintech.dao.ConsolidatedDAO;
import com.fintech.dao.FileDAO;
import com.fintech.dao.OutputFileDAO;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDAO fileDao;

	@Autowired
	private OutputFileDAO outputDao; // for all output type files

	@Autowired
	private ConsolidatedDAO cnDao;

	
	

	public Stream<String> load(LocalDate from_date, LocalDate to_date, Object guide, String size_type,
			int is_test) {
		List<OutputFile> listOfFile = outputDao.fetchOPFile(from_date, to_date, guide, size_type, is_test);

		if (listOfFile.size() == 0)
			return null;
		else {
			//ByteArrayInputStream in = ExcelHelper.OPFileToCSV(listOfFile);
			Stream<String> csvData=ExcelHelper.OPFileToCSV(listOfFile);
			return csvData;
			//return in;
		}
		
	}

	@Override
	public List<OutputFile> getDailyOPFile(LocalDate from_date, LocalDate to_date, Object guide, String size_type,
			int is_test) {
		// TODO Auto-generated method stub
		return outputDao.fetchOPFile(from_date, to_date, guide, size_type, is_test);
	}

	@Override
	public List<OutputFile> getMonthlyOPFile(LocalDate from_date, LocalDate to_date, Object guide, String size_type,
			int is_test) {
		// TODO Auto-generated method stub

		return outputDao.fetchOPFile(from_date, to_date, guide, size_type, is_test);
	}

	@Override
	public List<ConsolidatedOPFile> getConsolidatedOP(String from_date, String to_date, String type) {
		// TODO Auto-generated method stub
		return cnDao.fetchConsolidatedOP(from_date, to_date, type);

		// ByteArrayInputStream in = ExcelHelper.consolidatedOP(listOfFile);
		// return in;
	}

	@Override
	public List<DailyClientProcessingFile> getDailyClientFile(String event, int status, LocalDateTime from_dateTime,
			LocalDateTime to_dateTime) {
		// TODO Auto-generated method stub
		return fileDao.fetchDailyClientFile(event, status, from_dateTime, to_dateTime);
	}

	@Override
	public List<DailyClientProcessingFile> getDailyMonthlyOPFilesStat(String event_type, int test, LocalDateTime from_dateTime,
			LocalDateTime to_dateTime) {
		// TODO Auto-generated method stub

		return fileDao.fetchOPFileList(event_type, test, from_dateTime, to_dateTime);
	}

	
	
	
	/*
	 * public void downloadFileFromSFTP(String sftpHost, int sftpPort, String
	 * sftpUsername, String sftpPassword, String parentFolderPath, String
	 * compressedFileName, HttpServletResponse response) throws IOException { try
	 * (SftpClient sftpClient =
	 * SftpClientFactory.instance().createSftpClient(sftpHost, sftpPort,
	 * sftpUsername, sftpPassword)) { // Change directory to the parent folder
	 * sftpClient.changeDirectory(parentFolderPath);
	 * 
	 * // Download the compressed file byte[] compressedFileData =
	 * sftpClient.read(compressedFileName);
	 * 
	 * // Decompress the file byte[] decompressedFileData =
	 * decompressFile(compressedFileData);
	 * 
	 * // Set the content type and headers for the response
	 * response.setContentType("application/vnd.ms-excel");
	 * response.setHeader("Content-Disposition",
	 * "attachment; filename=\"your-file-name.xlsx\"");
	 * 
	 * // Write the file data to the response output stream try (OutputStream
	 * outputStream = response.getOutputStream()) {
	 * outputStream.write(decompressedFileData); } } }
	 * 
	 * private byte[] decompressFile(byte[] compressedData) throws IOException { try
	 * (InputStream inputStream = new ByteArrayInputStream(compressedData);
	 * ArchiveInputStream archiveInputStream = new
	 * ArchiveStreamFactory().createArchiveInputStream(inputStream)) { ArchiveEntry
	 * entry; while ((entry = archiveInputStream.getNextEntry()) != null) { if
	 * (!archiveInputStream.canReadEntryData(entry)) { continue; } if
	 * (entry.getName().endsWith(".xlsx")) { ByteArrayOutputStream outputStream =
	 * new ByteArrayOutputStream(); IOUtils.copy(archiveInputStream, outputStream);
	 * return outputStream.toByteArray(); } } } throw new
	 * IOException("Excel file not found in the compressed folder."); }
	 */

}
