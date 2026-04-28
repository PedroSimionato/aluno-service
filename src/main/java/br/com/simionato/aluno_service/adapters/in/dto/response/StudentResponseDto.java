package br.com.simionato.aluno_service.adapters.in.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record StudentResponseDto(
        UUID id,
        String name,
        String documentNumber,
        String email,
        String academicLevel,
        LocalDate birthDate,
        AddressResponse address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
