package com.javidkhalilov.panacea.service.impl;

import com.javidkhalilov.panacea.dao.entity.Clinic;
import com.javidkhalilov.panacea.dao.repository.ClinicRepository;
import com.javidkhalilov.panacea.dto.request.ClinicRequestDTO;
import com.javidkhalilov.panacea.dto.response.ClinicResponseDTO;
import com.javidkhalilov.panacea.exception.ClinicNotFoundException;
import com.javidkhalilov.panacea.mapper.ClinicMapper;
import com.javidkhalilov.panacea.service.ClinicService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClinicServiceImpl implements ClinicService {
    final ClinicMapper clinicMapper;
    final ClinicRepository clinicRepository;

    @Override
    public ClinicResponseDTO createClinic(ClinicRequestDTO clinicRequestDTO) {
        Clinic clinic=clinicMapper.toEntity(clinicRequestDTO);
        Clinic savedClinic=clinicRepository.save(clinic);
        return clinicMapper.entityToResponseDTO(savedClinic);
    }

    @Override
    public List<ClinicResponseDTO> getAllClinics() {
        return clinicMapper.entityListToResponseList(clinicRepository.findAll());
    }

    @Override
    public ClinicResponseDTO getClinicById(Long id) {
        var clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ClinicNotFoundException("Clinic not found"));
        return clinicMapper.entityToResponseDTO(clinic);
    }

    @Override
    public ClinicResponseDTO updateClinic(Long id, ClinicRequestDTO clinicRequestDTO) {
        var clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ClinicNotFoundException("Clinic not found"));

        clinicMapper.updateEntityFromDTO(clinicRequestDTO, clinic);

        Clinic updatedClinic = clinicRepository.save(clinic);
        return clinicMapper.entityToResponseDTO(updatedClinic);
    }

    @Override
    public void deleteClinicById(Long id) {
        if (!clinicRepository.existsById(id)) {
            throw new ClinicNotFoundException("Clinic not found");
        }
        clinicRepository.deleteById(id);
    }
}
