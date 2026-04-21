package br.com.simionato.aluno_service.domain.model;

import br.com.simionato.aluno_service.domain.exception.StudentException;
import br.com.simionato.aluno_service.domain.exception.enums.StudentErrorCode;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import static br.com.simionato.aluno_service.domain.utils.Validations.checkBlankFields;

@Getter
public class Student {
    private final UUID id;
    private final String name;
    private final String documentNumber;
    private final String email;
    private final String academicLevel;
    private final Address address;
    private final LocalDate birthDate;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Student(UUID id, String name, String documentNumber, String email, String academicLevel, Address address, LocalDate birthDate, LocalDateTime createdAt) {
        checkFields(name, documentNumber, email, academicLevel);
        checkBirthDate(birthDate);
        checkIfAddressIsNull(address);

        this.id = Objects.isNull(id) ? UUID.randomUUID() : id;
        this.name = name;
        this.documentNumber = documentNumber;
        this.email = email;
        this.academicLevel = academicLevel;
        this.address = address;
        this.birthDate = birthDate;
        this.createdAt = Objects.isNull(createdAt) ? LocalDateTime.now() : createdAt;
        this.updatedAt = LocalDateTime.now();
    }

    private static void checkFields(String name, String documentNumber, String email, String academicLevel){
        boolean fieldsInvalid = checkBlankFields(Stream.of(
                name,
                documentNumber,
                email,
                academicLevel
        ));

        if (fieldsInvalid) {
            throw new StudentException(StudentErrorCode.INVALID_STUDENT);
        }
    }

    private static void checkBirthDate(LocalDate birthDate){
        if(Objects.isNull(birthDate) || birthDate.isAfter(LocalDate.now())){
            throw new StudentException(StudentErrorCode.INVALID_STUDENT);
        }
    }

    private static void checkIfAddressIsNull(Address address){
        if(Objects.isNull(address)){
            throw new StudentException(StudentErrorCode.INVALID_STUDENT_ADDRESS);
        }
    }
}
