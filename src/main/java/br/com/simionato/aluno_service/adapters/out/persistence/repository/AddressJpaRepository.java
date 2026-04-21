package br.com.simionato.aluno_service.adapters.out.persistence.repository;

import br.com.simionato.aluno_service.adapters.out.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, UUID> {
    Optional<AddressEntity> findByStudentId(UUID studentId);
}
