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
import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.NonNull;
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

        Address updatedAddress = Objects.isNull(command.address()) ? existing.getAddress() : createUpdatedAddress(command, existing);

        Student updated = new Student(
                existing.getId(),
                StringUtils.isBlank(command.name()) ? existing.getName() : command.name(),
                existing.getDocumentNumber(),
                StringUtils.isBlank(command.email()) ? existing.getEmail() : command.email(),
                StringUtils.isBlank(command.academicLevel()) ? existing.getAcademicLevel() : command.academicLevel(),
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

    private static @NonNull Address createUpdatedAddress(UpdateStudentCommand command, Student existing) {
        return new Address(
                StringUtils.isBlank(command.address().street()) ? existing.getAddress().getStreet() : command.address().street(),
                StringUtils.isBlank(command.address().number()) ? existing.getAddress().getNumber() : command.address().number(),
                StringUtils.isBlank(command.address().complement()) ? existing.getAddress().getComplement() : command.address().complement(),
                StringUtils.isBlank(command.address().neighborhood()) ? existing.getAddress().getNeighborhood() : command.address().neighborhood(),
                StringUtils.isBlank(command.address().city()) ? existing.getAddress().getCity() : command.address().city(),
                StringUtils.isBlank(command.address().state()) ? existing.getAddress().getState() : command.address().state(),
                StringUtils.isBlank(command.address().zipcode()) ? existing.getAddress().getZipcode() : command.address().zipcode()
        );
    }
}
