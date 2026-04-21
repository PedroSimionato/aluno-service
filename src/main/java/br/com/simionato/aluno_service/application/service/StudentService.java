package br.com.simionato.aluno_service.application.service;

import br.com.simionato.aluno_service.domain.exception.StudentException;
import br.com.simionato.aluno_service.domain.exception.enums.StudentErrorCode;
import br.com.simionato.aluno_service.domain.model.Address;
import br.com.simionato.aluno_service.domain.model.Student;
import br.com.simionato.aluno_service.domain.ports.in.CreateStudentUseCase;
import br.com.simionato.aluno_service.domain.ports.in.DeleteStudentUseCase;
import br.com.simionato.aluno_service.domain.ports.in.GetStudentUseCase;
import br.com.simionato.aluno_service.domain.ports.in.UpdateStudentUseCase;
import br.com.simionato.aluno_service.domain.ports.in.command.*;
import br.com.simionato.aluno_service.domain.ports.out.StudentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentService implements
        CreateStudentUseCase,
        GetStudentUseCase,
        UpdateStudentUseCase,
        DeleteStudentUseCase {

    private final StudentRepositoryPort repositoryPort;

    @Override
    public Student execute(CreateStudentCommand command) {
        repositoryPort.findByDocumentNumber(command.documentNumber())
                .ifPresent(student -> {
                    throw new StudentException(StudentErrorCode.STUDENT_ALREADY_EXISTS, command.documentNumber());
                });

        Address address = new Address(
                command.address().street(),
                command.address().number(),
                command.address().complement(),
                command.address().neighborhood(),
                command.address().city(),
                command.address().state(),
                command.address().zipcode()
        );

        Student student = new Student(
                null,
                command.name(),
                command.documentNumber(),
                command.email(),
                command.academicLevel(),
                address,
                command.birthDate(),
                null
        );

        return repositoryPort.save(student);
    }

    @Override
    public Student execute(UpdateStudentCommand command) {
        Student existing = repositoryPort.findById(command.id())
                .orElseThrow(() -> new StudentException(StudentErrorCode.STUDENT_NOT_FOUND, command.id()));

        Address updatedAddress = new Address(
                command.address().street().isBlank() ? existing.getAddress().getStreet() : command.address().street(),
                command.address().number().isBlank() ? existing.getAddress().getNumber() : command.address().number(),
                command.address().complement().isBlank() ? existing.getAddress().getComplement() : command.address().complement(),
                command.address().neighborhood().isBlank() ? existing.getAddress().getNeighborhood() : command.address().neighborhood(),
                command.address().city().isBlank() ? existing.getAddress().getCity() : command.address().city(),
                command.address().state().isBlank() ? existing.getAddress().getState() : command.address().state(),
                command.address().zipcode().isBlank() ? existing.getAddress().getZipcode() : command.address().zipcode()
        );

        Student updated = new Student(
                existing.getId(),
                command.name().isBlank() ? existing.getName() : command.name(),
                existing.getDocumentNumber(),
                command.email().isBlank()? existing.getEmail() : command.email(),
                command.academicLevel().isBlank()? existing.getAcademicLevel() : command.academicLevel(),
                updatedAddress,
                Objects.isNull(command.birthDate()) ? existing.getBirthDate() : command.birthDate(),
                existing.getCreatedAt()
        );

        return repositoryPort.update(updated)
                .orElseThrow(() -> new StudentException(StudentErrorCode.STUDENT_NOT_FOUND, command.id()));
    }

    @Override
    public Student findById(FindStudentByIdCommand command) {

        return repositoryPort.findById(command.id())
                .orElseThrow(() -> new StudentException(StudentErrorCode.STUDENT_NOT_FOUND, command.id()));
    }

    @Override
    public Student findByDocumentNumber(FindStudentByDocumentNumberCommand command) {

        return repositoryPort.findByDocumentNumber(command.documentNumber())
                .orElseThrow(() -> new StudentException(StudentErrorCode.STUDENT_NOT_FOUND, command.documentNumber()));
    }

    @Override
    public List<Student> findAll() {

        return repositoryPort.findAll();
    }

    @Override
    public void execute(DeleteStudentCommand command) {
        repositoryPort.findById(command.id())
                .orElseThrow(() -> new StudentException(StudentErrorCode.STUDENT_NOT_FOUND, command.id()));

        repositoryPort.deleteById(command.id());

    }
}
