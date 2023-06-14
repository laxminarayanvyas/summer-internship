package com.fintech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.beans.DailyClientProcessingFile;
import com.fintech.dao.FileDAO;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDAO fileDao;
	@Override
	public List<DailyClientProcessingFile> getDailyClientFile(String event_type, int is_test) {
		// TODO Auto-generated method stub
		return fileDao.fetchDailyClientFile(event_type,is_test);
	}

}
