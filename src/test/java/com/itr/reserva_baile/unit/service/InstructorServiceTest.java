package com.itr.reserva_baile.unit.service;

import com.itr.reserva_baile.model.Instructor;
import com.itr.reserva_baile.repository.InstructorRepository;
import com.itr.reserva_baile.service.InstructorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InstructorServiceTest {

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorService instructorService;

    private Instructor instructorMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        instructorMock = new Instructor(1L, "Carlos", "Salsa", 5, "contacto@ejemplo.com");
    }

    @Test
    void whenCreateInstructor_thenReturnInstructor() {
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructorMock);

        Instructor result = instructorService.createInstructor(instructorMock);

        assertNotNull(result);
        assertEquals(instructorMock.getId(), result.getId());
        verify(instructorRepository).save(any(Instructor.class));
    }

    @Test
    void whenGetAllInstructors_thenReturnList() {
        when(instructorRepository.findAll()).thenReturn(java.util.List.of(instructorMock));

        Iterable<Instructor> result = instructorService.getAllInstructors();

        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
    }

    @Test
    void whenGetInstructorById_thenReturnInstructor() {
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructorMock));

        Optional<Instructor> result = instructorService.getInstructorById(1L);

        assertTrue(result.isPresent());
        assertEquals(instructorMock.getId(), result.get().getId());
        assertEquals(instructorMock.getNombre(), result.get().getNombre());
    }

    @Test
    void whenUpdateInstructor_thenReturnUpdatedInstructor() {
        Instructor updatedInstructor = new Instructor(1L, "Carlos Actualizado", "Salsa", 6, "contacto@actualizado.com");
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructorMock));
        when(instructorRepository.save(any(Instructor.class))).thenReturn(updatedInstructor);

        Instructor result = instructorService.updateInstructor(1L, updatedInstructor);

        assertNotNull(result);
        assertEquals("Carlos Actualizado", result.getNombre());
    }

    @Test
    void whenDeleteInstructor_thenVerifyDelete() {
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructorMock));
        doNothing().when(instructorRepository).deleteById(1L);

        instructorService.deleteInstructor(1L);

        verify(instructorRepository).deleteById(1L);
    }
}
