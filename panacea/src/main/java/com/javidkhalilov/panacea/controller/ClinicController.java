package com.javidkhalilov.panacea.controller;

import com.javidkhalilov.panacea.entity.Clinic;
import com.javidkhalilov.panacea.service.ClinicService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/clinic")
public class ClinicController {

    final ClinicService clinicService;

    @PostMapping
    public ResponseEntity<Clinic> createClinic(@RequestBody Clinic clinic) {
        Clinic savedClinic=clinicService.createClinic(clinic);
        return ResponseEntity.ok(savedClinic);
    }

    @GetMapping
    public ResponseEntity<List<Clinic>> getAllClinics() {
        return ResponseEntity.ok(clinicService.getAllClinics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clinic> getClinicById(@PathVariable Long id) {
        return ResponseEntity.ok(clinicService.getClinicById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clinic> updateClinic(@PathVariable Long id, @RequestBody Clinic updatedClinic) {
        return ResponseEntity.ok(clinicService.updateClinic(id, updatedClinic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicById(@PathVariable Long id) {
        clinicService.deleteClinicById(id);
        return ResponseEntity.noContent().build();
    }
}
