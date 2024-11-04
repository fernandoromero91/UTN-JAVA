package com.itr.reserva_baile.unit.repository;

import com.itr.reserva_baile.model.Instructor;
import com.itr.reserva_baile.repository.InstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class InstructorRepositoryTest {

    @Autowired
    private InstructorRepository instructorRepository;

    private Instructor instructor;

    @BeforeEach
    void setUp() {
        instructor = new Instructor(null, "Laura", "Bachata", 4, "laura@ejemplo.com");
    }

    @Test
    void testSaveInstructor() {
        Instructor savedInstructor = instructorRepository.save(instructor);
        assertNotNull(savedInstructor.getId());
        assertEquals("Laura", savedInstructor.getNombre());
    }

    @Test
    void testFindById() {
        Instructor savedInstructor = instructorRepository.save(instructor);
        Optional<Instructor> foundInstructor = instructorRepository.findById(savedInstructor.getId());

        assertTrue(foundInstructor.isPresent());
        assertEquals(savedInstructor.getNombre(), foundInstructor.get().getNombre());
    }

    @Test
    void testDeleteInstructor() {
        Instructor savedInstructor = instructorRepository.save(instructor);
        instructorRepository.deleteById(savedInstructor.getId());
        Optional<Instructor> foundInstructor = instructorRepository.findById(savedInstructor.getId());

        assertFalse(foundInstructor.isPresent());
    }
}
