package com.fintech.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.beans.DailyClientProcessingFile;
import com.fintech.beans.OutputFile;
import com.fintech.dao.FileDAO;
import com.fintech.dao.OutputFileDAO;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDAO fileDao;
	
	@Autowired
	private OutputFileDAO outputDao; //for all output type files
	
	
	@Override
	public List<DailyClientProcessingFile> getDailyClientFile(String event_type, int is_test) {
		// TODO Auto-generated method stub
		return fileDao.fetchDailyClientFile(event_type,is_test);
	}
	@Override
	public List<OutputFile> getDailyOPFile(LocalDate to_date, LocalDate from_date, String size_type,
			Object guide, int is_test) {
		// TODO Auto-generated method stub
		return outputDao.fetchDailyOPFile(to_date,from_date,size_type, guide, is_test);
	}
	

}
