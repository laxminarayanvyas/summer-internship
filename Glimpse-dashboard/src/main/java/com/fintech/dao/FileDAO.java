package com.fintech.dao;

//import java.time.LocalDate;
//import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fintech.beans.DailyClientProcessingFile;
//import com.fintech.beans.DailyOPFile;

public interface FileDAO extends CrudRepository<DailyClientProcessingFile, Integer> {
	
	@Query(value="CALL proc_web_get_sftpparser_statistics(:event_type, :is_test)",nativeQuery = true)
	List<DailyClientProcessingFile> fetchDailyClientFile(@Param("event_type") String event_type, @Param("is_test") int is_test);

	
	
	
}
