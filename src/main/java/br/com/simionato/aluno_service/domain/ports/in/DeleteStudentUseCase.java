package br.com.simionato.aluno_service.domain.ports.in;

import br.com.simionato.aluno_service.domain.ports.in.command.DeleteStudentCommand;

import java.util.UUID;

public interface DeleteStudentUseCase {
    void execute(DeleteStudentCommand command);
}
