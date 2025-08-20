package com.javidkhalilov.panacea.dao.repository;

import com.javidkhalilov.panacea.dao.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}
