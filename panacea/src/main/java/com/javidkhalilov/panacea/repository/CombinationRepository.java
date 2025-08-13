package com.javidkhalilov.panacea.repository;

import com.javidkhalilov.panacea.entity.Combination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombinationRepository extends JpaRepository<Combination, Long> {
}
