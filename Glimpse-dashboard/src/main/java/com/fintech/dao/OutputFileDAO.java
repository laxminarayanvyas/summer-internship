package com.fintech.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fintech.beans.OutputFile;

public interface OutputFileDAO extends JpaRepository<OutputFile, String> {
	
	@Query(value="CALL proc_web_get_generate_output_file_data(:from_date, :to_date, :guide, :size_type, :is_test)",nativeQuery = true)
	List<OutputFile> fetchOPFile(@Param("from_date") LocalDate from_date, @Param("to_date") LocalDate to_date,
			 @Param("guide") Object guide,@Param("size_type") String size_type,
			@Param("is_test") int is_test);

	
}
