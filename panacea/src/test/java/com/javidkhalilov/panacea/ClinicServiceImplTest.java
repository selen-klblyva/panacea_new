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


   /* @Test
    void testGetAllClinics() {
        Clinic clinic = new Clinic();
        clinic.setId(1L);
        clinic.setName("Panacea");
        clinic.setAddress("Baku");
        clinic.setContactNumber("123456789");
        List<Clinic> clinicEntities = List.of(clinic);

        ClinicResponseDTO clinicResponse = new ClinicResponseDTO(
                1L, "Panacea", "Baku", "123456789"
        );
        List<ClinicResponseDTO> clinicResponseDTOs = List.of(clinicResponse);

        when(clinicRepository.findAll()).thenReturn(clinicEntities);
        when(clinicMapper.entityListToResponseList(clinicEntities)).thenReturn(clinicResponseDTOs);

        List<ClinicResponseDTO> result = clinicServiceImpl.getAllClinics();

        assertEquals(clinicResponseDTOs, result);
        assertEquals(1, result.size());
        assertEquals("Panacea", result.get(0).name());
    }

    */
    
    /* @Test
    void testGetClinicById_success() {
        Clinic clinic = new Clinic();
        clinic.setId(1L);
        clinic.setName("Panacea");
        clinic.setAddress("Baku");
        clinic.setContactNumber("123456789");

        ClinicResponseDTO clinicResponse = new ClinicResponseDTO(
                1L, "Panacea", "Baku", "123456789"
        );

        when(clinicRepository.findById(1L)).thenReturn(Optional.of(clinic));
        when(clinicMapper.entityToResponseDTO(clinic)).thenReturn(clinicResponse);

        ClinicResponseDTO result = clinicServiceImpl.getClinicById(1L);

        assertEquals(clinicResponse, result);
        assertEquals("Panacea", result.name());
    }

    @Test
    void testGetClinicById_notFound() {
        when(clinicRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ClinicNotFoundException.class, () -> clinicServiceImpl.getClinicById(1L));
    }

     */

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


   /* @Test
    void testDeleteClinicById_success() {
        Long clinicId = 1L;
        when(clinicRepository.existsById(clinicId)).thenReturn(true);
        clinicServiceImpl.deleteClinicById(clinicId);

        verify(clinicRepository, times(1)).deleteById(clinicId);
    }


    @Test
    void testDeleteClinicById_notFound() {
        Long clinicId = 1L;
        when(clinicRepository.existsById(clinicId)).thenReturn(false);

        assertThrows(ClinicNotFoundException.class, () -> clinicServiceImpl.deleteClinicById(clinicId));

        verify(clinicRepository, never()).deleteById(anyLong());
    }

    */

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

   /* @Test
    void testCreateClinic() {
        ClinicRequestDTO requestDTO = new ClinicRequestDTO("City Hospital", "Baku", "123456789");
        Clinic clinic = new Clinic(1L, "City Hospital", "Baku", "123456789");
        ClinicResponseDTO responseDTO = new ClinicResponseDTO(1L, "City Hospital", "Baku", "123456789");

        when(clinicMapper.toEntity(requestDTO)).thenReturn(clinic);
        when(clinicRepository.save(clinic)).thenReturn(clinic);
        when(clinicMapper.entityToResponseDTO(clinic)).thenReturn(responseDTO);

        ClinicResponseDTO result = clinicServiceImpl.createClinic(requestDTO);

        assertEquals(responseDTO, result);

        verify(clinicMapper, times(1)).toEntity(requestDTO);
        verify(clinicRepository, times(1)).save(clinic);
        verify(clinicMapper, times(1)).entityToResponseDTO(clinic);
    }

    */

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


   /* @Test
    void testUpdateClinic_success() {
        Long clinicId = 1L;
        ClinicRequestDTO requestDTO = new ClinicRequestDTO("Updated Clinic", "Ganja", "987654321");
        Clinic clinic = new Clinic(1L, "Old Clinic", "Baku", "123456789");
        Clinic updatedClinic = new Clinic(1L, "Updated Clinic", "Ganja", "987654321");
        ClinicResponseDTO responseDTO = new ClinicResponseDTO(1L, "Updated Clinic", "Ganja", "987654321");

        when(clinicRepository.findById(clinicId)).thenReturn(Optional.of(clinic));

        doAnswer(invocation -> {
            ClinicRequestDTO dto = invocation.getArgument(0);
            Clinic entity = invocation.getArgument(1);
            entity.setName(dto.name());
            entity.setAddress(dto.address());
            entity.setContactNumber(dto.contactNumber());
            return null;
        }).when(clinicMapper).updateEntityFromDTO(requestDTO, clinic);

        when(clinicRepository.save(clinic)).thenReturn(updatedClinic);
        when(clinicMapper.entityToResponseDTO(updatedClinic)).thenReturn(responseDTO);

        ClinicResponseDTO result = clinicServiceImpl.updateClinic(clinicId, requestDTO);

        assertEquals(responseDTO, result);
        assertEquals("Updated Clinic", result.name());
        assertEquals("Ganja", result.address());

        verify(clinicRepository, times(1)).findById(clinicId);
        verify(clinicMapper, times(1)).updateEntityFromDTO(requestDTO, clinic);
        verify(clinicRepository, times(1)).save(clinic);
        verify(clinicMapper, times(1)).entityToResponseDTO(updatedClinic);
    }

    @Test
    void testUpdateClinic_notFound() {
        Long clinicId = 1L;
        ClinicRequestDTO requestDTO = new ClinicRequestDTO("Updated Clinic", "Ganja", "987654321");

        when(clinicRepository.findById(clinicId)).thenReturn(Optional.empty());

        assertThrows(ClinicNotFoundException.class, () -> clinicServiceImpl.updateClinic(clinicId, requestDTO));

        verify(clinicRepository, times(1)).findById(clinicId);
        verify(clinicMapper, never()).updateEntityFromDTO(any(), any());
        verify(clinicRepository, never()).save(any());
    }
    */

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

