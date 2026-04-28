package br.com.simionato.aluno_service.adapters.in.dto.request;

public record AddressRequestDto(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipcode
) {
}
