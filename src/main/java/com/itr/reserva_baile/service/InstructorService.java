package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.Instructor;
import com.itr.reserva_baile.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Iterable<Instructor> getAllInstructores() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(Long id, Instructor instructorDetails) {
        return instructorRepository.findById(id)
                .map(instructor -> {
                    instructor.setNombre(instructorDetails.getNombre());
                    instructor.setEspecialidad(instructorDetails.getEspecialidad());
                    instructor.setExperiencia(instructorDetails.getExperiencia());
                    instructor.setContacto(instructorDetails.getContacto());
                    return instructorRepository.save(instructor);
                }).orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
    }

    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
}
