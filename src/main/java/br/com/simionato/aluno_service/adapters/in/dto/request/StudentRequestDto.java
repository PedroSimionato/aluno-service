package br.com.simionato.aluno_service.adapters.in.dto.request;

public record StudentRequestDto(
        String name,
        String documentNumber,
        String email,
        String academicLevel,
        String birthDate,
        AddressRequestDto address
) {
}
