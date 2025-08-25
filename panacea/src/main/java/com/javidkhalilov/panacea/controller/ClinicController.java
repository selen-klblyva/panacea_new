package com.javidkhalilov.panacea.controller;

import com.javidkhalilov.panacea.dto.request.ClinicRequestDTO;
import com.javidkhalilov.panacea.dto.response.ClinicResponseDTO;
import com.javidkhalilov.panacea.service.ClinicService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/clinics")
public class ClinicController {

    final ClinicService clinicService;

    @PostMapping
    public ResponseEntity<ClinicResponseDTO> createClinic(@RequestBody ClinicRequestDTO clinicRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clinicService.createClinic(clinicRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ClinicResponseDTO>> getAllClinics() {
        return ResponseEntity.status(HttpStatus.OK).body(clinicService.getAllClinics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicResponseDTO> getClinicById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clinicService.getClinicById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicResponseDTO> updateClinic(@PathVariable Long id, @RequestBody ClinicRequestDTO clinicRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(clinicService.updateClinic(id, clinicRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicById(@PathVariable Long id) {
        clinicService.deleteClinicById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
