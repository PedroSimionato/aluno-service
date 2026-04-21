package br.com.simionato.aluno_service.domain.ports.in.command;

import java.time.LocalDate;

public record CreateStudentCommand(
        String name,
        String documentNumber,
        String email,
        String academicLevel,
        CreateAddressCommand address,
        LocalDate birthDate
){}


