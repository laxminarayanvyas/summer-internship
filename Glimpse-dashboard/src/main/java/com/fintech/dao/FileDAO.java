package com.fintech.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.time.LocalDate;
//import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fintech.beans.DailyClientProcessingFile;
//import com.fintech.beans.DailyOPFile;

public interface FileDAO extends CrudRepository<DailyClientProcessingFile, Integer> {
	
	@Query(value="CALL proc_web_get_sftpparser_statistics_filter_by_datetime(:event_type, :is_test,:from_date, :to_date)",nativeQuery = true)
	 List<DailyClientProcessingFile> fetchDailyClientFile(@Param("event_type") String event_type, @Param("is_test") int is_test,
			 @Param("from_date") LocalDateTime from_date, @Param("to_date") LocalDateTime to_date);
	/*
	 * @Query(value="SELECT * \r\n" + "FROM sftpparser_statistics as ncam\r\n" +
	 * "WHERE ncam.process_status IS NOT NULL\r\n" +
	 * "		AND ncam.user_name IS NOT NULL\r\n" +
	 * "		AND ncam.is_active = 1\r\n" + "		AND ncam.is_test=:test\r\n" +
	 * "		AND  ncam.event_type=:event\r\n" +
	 * "		AND  ncam.entry_dt_time>=:from_date\r\n" +
	 * "		AND ncam.entry_dt_time<=:to_date\r\n" + "		ORDER BY \r\n" +
	 * "			ncam.sftpparser_statistics_id asc",nativeQuery = true)
	 * List<DailyClientProcessingFile> fetchDailyClientFile(@Param("test") int
	 * test, @Param("event") String event,
	 * 
	 * @Param("from_date") LocalDate from_date, @Param("to_date") LocalDate
	 * to_date);
	 */

	@Query(value="CALL proc_web_get_sftpparser_statistics_filter_by_datetime(:event_type,:is_test,:from_date,:to_date)",nativeQuery = true)
	List<DailyClientProcessingFile> fetchOPFileList(@Param("event_type") String event_type,@Param("is_test") int is_test,
			@Param("from_date") LocalDateTime from_date,@Param("to_date") LocalDateTime to_date);
	
	
//	List<DailyClientProcessingFile> findTopByOrderByUpdatedDateDesc(String event_type);
	
//	List<DailyClientProcessingFile> findAllByOrderByUpdatedDateDesc();
	
	
}
