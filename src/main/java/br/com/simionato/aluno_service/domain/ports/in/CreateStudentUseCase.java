package br.com.simionato.aluno_service.domain.ports.in;

import br.com.simionato.aluno_service.domain.model.Student;
import br.com.simionato.aluno_service.domain.ports.in.command.CreateStudentCommand;

public interface CreateStudentUseCase {
    Student execute(CreateStudentCommand command);
}
