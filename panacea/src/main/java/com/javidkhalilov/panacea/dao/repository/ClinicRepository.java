package com.javidkhalilov.panacea.dao.repository;

import com.javidkhalilov.panacea.dao.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
