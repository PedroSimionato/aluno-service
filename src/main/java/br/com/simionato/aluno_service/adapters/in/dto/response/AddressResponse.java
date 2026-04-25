package br.com.simionato.aluno_service.adapters.in.dto.response;

import java.util.UUID;

public record AddressResponse(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipcode
) {
}
