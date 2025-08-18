package com.javidkhalilov.panacea.service;


import com.javidkhalilov.panacea.entity.Clinic;

import java.util.List;

public interface ClinicService {
    Clinic createClinic(Clinic clinic);

    List<Clinic> getAllClinics();

    Clinic getClinicById(Long id);

    Clinic updateClinic(Long id, Clinic updatedClinic);

    void deleteClinicById(Long id);
}
