package br.com.simionato.aluno_service.domain.ports.in.command;

import java.util.UUID;

public record FindStudentByIdCommand(UUID id) {
}
