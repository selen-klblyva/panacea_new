package com.javidkhalilov.panacea.repository;

import com.javidkhalilov.panacea.entity.CombinationAnalysis;
import com.javidkhalilov.panacea.entity.CombinationAnalysisId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombinationAnalysisRepository extends JpaRepository<CombinationAnalysis, CombinationAnalysisId> {

}
