package com.javidkhalilov.panacea;

import com.javidkhalilov.panacea.dao.entity.Clinic;
import com.javidkhalilov.panacea.dao.repository.ClinicRepository;
import com.javidkhalilov.panacea.dto.request.ClinicRequestDTO;
import com.javidkhalilov.panacea.dto.response.ClinicResponseDTO;
import com.javidkhalilov.panacea.exception.ClinicNotFoundException;
import com.javidkhalilov.panacea.mapper.ClinicMapper;
import com.javidkhalilov.panacea.service.impl.ClinicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

class ClinicServiceImplTest {

    @Mock
    private ClinicRepository clinicRepository;

    @Mock
    private ClinicMapper clinicMapper;

    @InjectMocks
    private ClinicServiceImpl clinicServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should get all clinics successfully")
    void testGetAllClinics() {
        Clinic clinic = new Clinic();
        List<Clinic> clinicEntities = List.of(clinic);

        ClinicResponseDTO clinicResponse = new ClinicResponseDTO(1L, "Panacea", "Baku", "123456789");
        List<ClinicResponseDTO> clinicResponseDTOs = List.of(clinicResponse);

        when(clinicRepository.findAll()).thenReturn(clinicEntities);
        when(clinicMapper.entityListToResponseList(anyList())).thenReturn(clinicResponseDTOs);

        List<ClinicResponseDTO> result = clinicServiceImpl.getAllClinics();

        assertNotNull(result);
        verify(clinicRepository).findAll();
        verify(clinicMapper).entityListToResponseList(anyList());
    }

    @Test
    @DisplayName("Should get clinic by id successfully")
    void testGetClinicById_success() {
        Clinic clinic = new Clinic();
        List<Clinic> clinics = List.of(clinic);

        ClinicResponseDTO clinicResponse = mock(ClinicResponseDTO.class);

        when(clinicRepository.findById(anyLong())).thenReturn(Optional.of(clinic));
        when(clinicMapper.entityToResponseDTO(any(Clinic.class))).thenReturn(clinicResponse);

        ClinicResponseDTO result = clinicServiceImpl.getClinicById(1L);

        assertNotNull(result);
        verify(clinicRepository).findById(anyLong());
        verify(clinicMapper).entityToResponseDTO(any(Clinic.class));
    }

    @Test
    @DisplayName("Should throw ClinicNotFoundException if clinic not found")
    void testGetClinicById_notFound() {
        when(clinicRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ClinicNotFoundException.class, () -> clinicServiceImpl.getClinicById(1L));

        verify(clinicRepository).findById(anyLong());
    }

    @Test
    @DisplayName("Should delete clinic by id successfully")
    void testDeleteClinicById_success() {

        when(clinicRepository.existsById(anyLong())).thenReturn(true);

        clinicServiceImpl.deleteClinicById(1L);

        verify(clinicRepository, times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("Should throw ClinicNotFoundException if clinic not found")
    void testDeleteClinicById_notFound() {

        when(clinicRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ClinicNotFoundException.class, () -> clinicServiceImpl.deleteClinicById(1L));

        verify(clinicRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Should create clinic successfully")
    void testCreateClinic() {

        ClinicRequestDTO requestDTO = mock(ClinicRequestDTO.class);
        Clinic clinic = mock(Clinic.class);
        ClinicResponseDTO responseDTO = mock(ClinicResponseDTO.class);

        when(clinicMapper.toEntity(any(ClinicRequestDTO.class))).thenReturn(clinic);
        when(clinicRepository.save(any(Clinic.class))).thenReturn(clinic);
        when(clinicMapper.entityToResponseDTO(any(Clinic.class))).thenReturn(responseDTO);

        ClinicResponseDTO result = clinicServiceImpl.createClinic(requestDTO);

        assertNotNull(result);
        verify(clinicMapper, times(1)).toEntity(any(ClinicRequestDTO.class));
        verify(clinicRepository, times(1)).save(any(Clinic.class));
        verify(clinicMapper, times(1)).entityToResponseDTO(any(Clinic.class));
    }

    @Test
    @DisplayName("Should update clinic successfully")
    void testUpdateClinic_success() {

        ClinicRequestDTO requestDTO = mock(ClinicRequestDTO.class);
        Clinic clinic = mock(Clinic.class);
        Clinic updatedClinic = mock(Clinic.class);
        ClinicResponseDTO responseDTO = mock(ClinicResponseDTO.class);

        when(clinicRepository.findById(anyLong())).thenReturn(Optional.of(clinic));

        doAnswer(invocation -> {
            ClinicRequestDTO dto = invocation.getArgument(0);
            Clinic entity = invocation.getArgument(1);
            return null;
        }).when(clinicMapper).updateEntityFromDTO(any(ClinicRequestDTO.class), any(Clinic.class));

        when(clinicRepository.save(any(Clinic.class))).thenReturn(updatedClinic);
        when(clinicMapper.entityToResponseDTO(any(Clinic.class))).thenReturn(responseDTO);

        ClinicResponseDTO result = clinicServiceImpl.updateClinic(1L, requestDTO);

        assertNotNull(result);
        verify(clinicRepository, times(1)).findById(anyLong());
        verify(clinicMapper, times(1)).updateEntityFromDTO(any(ClinicRequestDTO.class), any(Clinic.class));
        verify(clinicRepository, times(1)).save(any(Clinic.class));
        verify(clinicMapper, times(1)).entityToResponseDTO(any(Clinic.class));
    }

    @Test
    @DisplayName("Should throw ClinicNotFoundException if clinic not found")
    void testUpdateClinic_notFound() {

        ClinicRequestDTO requestDTO = mock(ClinicRequestDTO.class);
        when(clinicRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ClinicNotFoundException.class, () -> clinicServiceImpl.updateClinic(1L, requestDTO));

        verify(clinicRepository, times(1)).findById(anyLong());
        verify(clinicMapper, never()).updateEntityFromDTO(any(), any());
        verify(clinicRepository, never()).save(any());
    }
}

