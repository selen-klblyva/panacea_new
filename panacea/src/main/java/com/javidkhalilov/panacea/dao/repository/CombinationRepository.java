package com.javidkhalilov.panacea.dao.repository;

import com.javidkhalilov.panacea.dao.entity.Combination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombinationRepository extends JpaRepository<Combination, Long> {
}
