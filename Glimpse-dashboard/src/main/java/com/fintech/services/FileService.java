package com.fintech.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fintech.beans.DailyClientProcessingFile;
import com.fintech.beans.OutputFile;

public interface FileService {
	List<DailyClientProcessingFile> getDailyClientFile(String event_type, int is_test);

	List<OutputFile> getDailyOPFile(LocalDate to_date, LocalDate from_date, String size_type, Object guide, int is_test);
}
