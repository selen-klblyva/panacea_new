package com.javidkhalilov.panacea.dao.repository;

import com.javidkhalilov.panacea.dao.entity.CombinationAnalysis;
import com.javidkhalilov.panacea.dao.entity.CombinationAnalysisId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombinationAnalysisRepository extends JpaRepository<CombinationAnalysis, CombinationAnalysisId> {

}

