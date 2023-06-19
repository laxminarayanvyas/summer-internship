package com.fintech.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fintech.beans.ConsolidatedOPFile;

public interface ConsolidatedDAO extends JpaRepository<ConsolidatedOPFile, Integer> {
	@Query(value="CALL proc_web_get_consolidated_output_file(:object1, :object2)",nativeQuery = true)
	List<ConsolidatedOPFile> fetchConsolidatedOP(@Param("object1") Object object1, @Param("object2") Object object2);
}
