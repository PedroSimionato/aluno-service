package br.com.simionato.aluno_service.application.service;

import br.com.simionato.aluno_service.domain.AcademicLevelEnum;
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
import java.util.Optional;

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

        repositoryPort.findByEmail(command.email())
                .ifPresent(student -> {
                    throw new StudentException(StudentErrorCode.EMAIL_ALREADY_EXISTS, command.email());
                });

        AcademicLevelEnum academicLevelEnum = AcademicLevelEnum.ofName(command.academicLevel());

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
                academicLevelEnum.name(),
                address,
                command.birthDate(),
                null
        );

        return repositoryPort.save(student);
    }

    @Override
    public Student execute(UpdateStudentCommand command) {

        repositoryPort.findByEmail(command.email())
                .ifPresent(student -> {
                    throw new StudentException(StudentErrorCode.EMAIL_ALREADY_EXISTS, command.email());
                });

        Student existing = repositoryPort.findById(command.id())
                .orElseThrow(() -> new StudentException(StudentErrorCode.STUDENT_NOT_FOUND, command.id()));

        String academicLevel = StringUtils.isBlank(command.academicLevel()) ?
                existing.getAcademicLevel() : AcademicLevelEnum.ofName(command.academicLevel()).name();

        Address updatedAddress = Objects.isNull(command.address()) ? existing.getAddress() : createUpdatedAddress(command, existing);

        Student updated = new Student(
                existing.getId(),
                Optional.ofNullable(command.name()).orElse(existing.getName()),
                existing.getDocumentNumber(),
                Optional.ofNullable(command.email()).orElse(existing.getEmail()),
                academicLevel,
                updatedAddress,
                Optional.ofNullable(command.birthDate()).orElse(existing.getBirthDate()),
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
                Optional.ofNullable(command.address().street()).orElse(existing.getAddress().getStreet()),
                Optional.ofNullable(command.address().number()).orElse(existing.getAddress().getNumber()),
                Optional.ofNullable(command.address().complement()).orElse(existing.getAddress().getComplement()),
                Optional.ofNullable(command.address().neighborhood()).orElse(existing.getAddress().getNeighborhood()),
                Optional.ofNullable(command.address().city()).orElse(existing.getAddress().getCity()),
                Optional.ofNullable(command.address().state()).orElse(existing.getAddress().getState()),
                Optional.ofNullable(command.address().zipcode()).orElse(existing.getAddress().getZipcode())
        );
    }
}
