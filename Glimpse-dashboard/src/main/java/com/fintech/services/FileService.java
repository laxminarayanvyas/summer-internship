package com.fintech.services;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fintech.beans.ConsolidatedOPFile;
import com.fintech.beans.DailyClientProcessingFile;
import com.fintech.beans.OutputFile;

public interface FileService {
	List<DailyClientProcessingFile> getDailyClientFile(String string, int i, LocalDateTime from_dateTime, LocalDateTime to_dateTime);

	List<OutputFile> getDailyOPFile(LocalDate from_date, LocalDate to_date, Object guide,String size_type, int is_test);

	List<OutputFile> getMonthlyOPFile(LocalDate from_date, LocalDate to_date,  Object guide,String size_type, int is_test);
	
	public ByteArrayInputStream load(LocalDate from_date, LocalDate to_date, Object guide,String size_type, int is_test);

	List<ConsolidatedOPFile> getConsolidatedOP(Object object, Object object2);

	DailyClientProcessingFile getDailyOPStat(String string, int i, LocalDateTime from_dateTime,
			LocalDateTime to_dateTime,int check);

	DailyClientProcessingFile getMonthlyOPStat(String string, int i, LocalDateTime from_dateTime,
			LocalDateTime to_dateTime,int check);

	

}
