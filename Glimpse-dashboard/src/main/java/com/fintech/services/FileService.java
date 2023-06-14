package com.fintech.services;

import java.util.List;

import com.fintech.beans.DailyClientProcessingFile;

public interface FileService {
	List<DailyClientProcessingFile> getDailyClientFile(String event_type, int is_test);
}
