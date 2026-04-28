package br.com.simionato.aluno_service.domain.ports.in;

import br.com.simionato.aluno_service.domain.model.Student;
import br.com.simionato.aluno_service.domain.ports.in.command.UpdateStudentCommand;

public interface UpdateStudentUseCase {
    Student execute(UpdateStudentCommand command);
}
