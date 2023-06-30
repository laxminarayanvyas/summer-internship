package com.fintech.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fintech.beans.ConsolidatedOPFile;

public interface ConsolidatedDAO extends JpaRepository<ConsolidatedOPFile, Integer> {
	@Query(value="CALL proc_web_get_consolidated_output_file(:from_date, :to_date, :type)",nativeQuery = true)
	List<ConsolidatedOPFile> fetchConsolidatedOP(@Param("from_date") String object1, @Param("to_date") String object2, @Param("type") String type);
}
