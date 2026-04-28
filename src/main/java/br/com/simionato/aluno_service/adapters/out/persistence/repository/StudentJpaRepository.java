package br.com.simionato.aluno_service.adapters.out.persistence.repository;

import br.com.simionato.aluno_service.adapters.out.persistence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentJpaRepository extends JpaRepository<StudentEntity, UUID> {

    Optional<StudentEntity> findByDocumentNumber(String documentNumber);
    Optional<StudentEntity> findByEmail(String email);
}
