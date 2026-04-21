package br.com.simionato.aluno_service.domain.ports.in.command;

public record CreateAddressCommand(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipcode
)
{}
