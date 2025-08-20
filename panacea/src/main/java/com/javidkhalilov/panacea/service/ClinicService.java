package com.javidkhalilov.panacea.service;

import com.javidkhalilov.panacea.dao.entity.Clinic;
import com.javidkhalilov.panacea.dto.request.ClinicRequestDTO;
import com.javidkhalilov.panacea.dto.response.ClinicResponseDTO;

import java.util.List;

public interface ClinicService {
    ClinicResponseDTO createClinic(ClinicRequestDTO clinicRequestDTO);

    List<ClinicResponseDTO> getAllClinics();

    ClinicResponseDTO getClinicById(Long id);

    ClinicResponseDTO updateClinic(Long id, ClinicRequestDTO clinicRequestDTO);

    void deleteClinicById(Long id);
}
