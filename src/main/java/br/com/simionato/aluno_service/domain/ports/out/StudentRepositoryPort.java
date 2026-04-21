package br.com.simionato.aluno_service.domain.ports.out;

import br.com.simionato.aluno_service.domain.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepositoryPort {
    Student save(Student student);
    Optional<Student> update(Student student);
    Optional<Student> findById(UUID id);
    Optional<Student> findByDocumentNumber(String documentNumber);
    List<Student> findAll();
    void deleteById(UUID id);

}
