package com.fintech.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fintech.beans.OutputFile;

public interface OutputFileDAO extends JpaRepository<OutputFile, String> {
	
	@Query(value="CALL proc_web_get_generate_output_file_data(:to_date, :from_date, :size_type, :guide, :is_test)",nativeQuery = true)
	List<OutputFile> fetchOPFile(@Param("to_date") LocalDate to_date, @Param("from_date") LocalDate from_date,
			@Param("size_type") String size_type, @Param("guide") Object guide,
			@Param("is_test") int is_test);
}
