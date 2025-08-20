package com.javidkhalilov.panacea.dao.repository;

import com.javidkhalilov.panacea.dao.entity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
}