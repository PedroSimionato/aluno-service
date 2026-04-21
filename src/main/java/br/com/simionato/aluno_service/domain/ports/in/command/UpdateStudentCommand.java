package br.com.simionato.aluno_service.domain.ports.in.command;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateStudentCommand (
        UUID id,
        String name,
        String email,
        String academicLevel,
        CreateAddressCommand address,
        LocalDate birthDate
){ }
