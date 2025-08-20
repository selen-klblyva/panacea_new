package com.javidkhalilov.panacea.mapper;

import com.javidkhalilov.panacea.dao.entity.Clinic;
import com.javidkhalilov.panacea.dto.request.ClinicRequestDTO;
import com.javidkhalilov.panacea.dto.response.ClinicResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClinicMapper {
    List<ClinicResponseDTO> entityListToResponseList(List<Clinic> clinics);

    ClinicResponseDTO entityToResponseDTO(Clinic clinic);

    Clinic toEntity(ClinicRequestDTO dto);

    void updateEntityFromDTO(ClinicRequestDTO clinicRequestDTO, @MappingTarget Clinic clinic);
}