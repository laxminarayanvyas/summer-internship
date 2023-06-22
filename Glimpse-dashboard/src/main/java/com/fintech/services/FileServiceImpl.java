package com.fintech.services;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public ByteArrayInputStream load(LocalDate from_date, LocalDate to_date, Object guide, String size_type,
			int is_test) {
		List<OutputFile> listOfFile = outputDao.fetchOPFile(from_date, to_date, guide, size_type, is_test);

		if (listOfFile.size() == 0)
			return null;
		else {
			ByteArrayInputStream in = ExcelHelper.OPFileToCSV(listOfFile);
			return in;
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
	public List<ConsolidatedOPFile> getConsolidatedOP(Object object, Object object2) {
		// TODO Auto-generated method stub
		return cnDao.fetchConsolidatedOP(object, object2);

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
	public DailyClientProcessingFile getDailyOPStat(String event_type, int test, LocalDateTime from_dateTime,
			LocalDateTime to_dateTime, int check) {
		// TODO Auto-generated method stub

		return findOPFile(fileDao.fetchOPFileList(event_type, test, from_dateTime, to_dateTime), check);
//		System.out.println(fileDao.findAllByOrderByUpdatedDateDesc());
//		return null;
	}

	private DailyClientProcessingFile findOPFile(List<DailyClientProcessingFile> fetchOPFileList, int check) {
		// TODO Auto-generated method stub
		if (fetchOPFileList.size() == 0)
			return null;
		DailyClientProcessingFile file;
		for (int i = 0; i < 2; i++) {
			file = fetchOPFileList.get(i);
			String status_detail = file.getStatus_details();
			int fileTypeStartIndex = status_detail.indexOf("\"fileType\":\"") + 12; // 12 is the length of
																					// "\"fileType\":\""
			int fileTypeEndIndex = status_detail.indexOf("\"", fileTypeStartIndex);
			String fileType = status_detail.substring(fileTypeStartIndex, fileTypeEndIndex);

			if (check == 1) {
				if (fileType.equalsIgnoreCase("Daily"))
					return file;
			} else {
				if (fileType.equalsIgnoreCase("Monthly"))
					return file;
			}

		}

		return null;
	}

	@Override
	public DailyClientProcessingFile getMonthlyOPStat(String event_type, int test, LocalDateTime from_dateTime,
			LocalDateTime to_dateTime, int check) {
		// TODO Auto-generated method stub
		return findOPFile(fileDao.fetchOPFileList(event_type, test, from_dateTime, to_dateTime), check);
	}

}
