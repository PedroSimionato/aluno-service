package br.com.simionato.aluno_service.adapters.out.persistence.adapter;

import br.com.simionato.aluno_service.adapters.out.persistence.mapper.StudentMapper;
import br.com.simionato.aluno_service.adapters.out.persistence.repository.StudentJpaRepository;
import br.com.simionato.aluno_service.domain.model.Student;
import br.com.simionato.aluno_service.domain.ports.out.StudentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentRespositoryAdapter implements StudentRepositoryPort {

    private final StudentJpaRepository repository;
    private final StudentMapper mapper;

    @Override
    public Student save(Student student) {
        var entity = mapper.toEntity(student);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Student> update(Student student) {
        return repository.findById(student.getId())
                .map(existing -> {
                    var updated = mapper.toEntity(student);
                    return mapper.toDomain(repository.save(updated));
                });
    }

    @Override
    public Optional<Student> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Student> findByDocumentNumber(String documentNumber) {
        return repository.findByDocumentNumber(documentNumber)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public List<Student> findAll() {
        System.out.println();
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
