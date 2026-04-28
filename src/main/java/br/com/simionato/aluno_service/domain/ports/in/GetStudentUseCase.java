package br.com.simionato.aluno_service.domain.ports.in;

import br.com.simionato.aluno_service.domain.model.Student;
import br.com.simionato.aluno_service.domain.ports.in.command.FindStudentByDocumentNumberCommand;
import br.com.simionato.aluno_service.domain.ports.in.command.FindStudentByIdCommand;

import java.util.List;

public interface GetStudentUseCase {
    Student findById(FindStudentByIdCommand id);
    Student findByDocumentNumber(FindStudentByDocumentNumberCommand documentNumber);
    List<Student> findAll();
}
