package com.javidkhalilov.panacea.service.impl;


import com.javidkhalilov.panacea.entity.Clinic;
import com.javidkhalilov.panacea.exception.ClinicNotFoundException;
import com.javidkhalilov.panacea.repository.ClinicRepository;
import com.javidkhalilov.panacea.service.ClinicService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClinicServiceImpl implements ClinicService {
    final ClinicRepository clinicRepository;

    @Override
    public Clinic createClinic(Clinic clinic) {
        validateClinic(clinic);
        return clinicRepository.save(clinic);
    }

    @Override
    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    @Override
    public Clinic getClinicById(Long id) {
        return clinicRepository.findById(id)
                .orElseThrow(() -> new ClinicNotFoundException(id));
    }

    @Override
    public Clinic updateClinic(Long id, Clinic updatedClinic) {
        Clinic clinic = getClinicById(id);
        if (StringUtils.hasText(updatedClinic.getName())) {
            clinic.setName(updatedClinic.getName());
        }
        if (updatedClinic.getAddress() != null) {
            clinic.setAddress(updatedClinic.getAddress());
        }
        if (updatedClinic.getContactNumber() != null) {
            clinic.setContactNumber(updatedClinic.getContactNumber());
        }
        return clinicRepository.save(clinic);
    }

    @Override
    public void deleteClinicById(Long id) {
        if (!clinicRepository.existsById(id)) {
            throw new ClinicNotFoundException(id);
        }
        clinicRepository.deleteById(id);
    }

    private void validateClinic(Clinic clinic) {
        if (!StringUtils.hasText(clinic.getName())) {
            throw new IllegalArgumentException("Clinic name cannot be empty");
        }
    }
}
