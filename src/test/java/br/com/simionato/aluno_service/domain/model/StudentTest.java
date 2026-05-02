package br.com.simionato.aluno_service.domain.model;

import br.com.simionato.aluno_service.domain.exception.StudentException;
import br.com.simionato.aluno_service.domain.exception.enums.StudentErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StudentTest {

    private Address address;

    @BeforeEach
    public void setup(){
        address = null;
        address = new Address("Rua itu", "415", null, "Baeta Neves", "Sao Bernardo do Campo", "Sao Paulo", "09751040");
    }

    @Test
    public void shouldCreateStudentWhenAllFieldAreValid(){
        Student student = new Student(UUID.randomUUID(),
                "Joao Silva",
                "12345678901",
                "joao@email.com",
                "GRADUATE",
                address,
                LocalDate.of(2000, 1, 1),
                LocalDateTime.of(2024, 1, 1, 0, 0));

        assertThat(student).isNotNull();
    }

    @Test
    public void shouldCreateStudentWhenIdIsNull(){
        Student student = new Student(null,
                "Joao Silva",
                "12345678901",
                "joao@email.com",
                "GRADUATE",
                address,
                LocalDate.of(2000, 1, 1),
                LocalDateTime.now());

        assertThat(student).isNotNull();
    }

    @Test
    public void shouldCreateStudentWhenCreatedAtIsNull(){
        Student student = new Student(UUID.randomUUID(),
                "Joao Silva",
                "12345678901",
                "joao@email.com",
                "GRADUATE",
                address,
                LocalDate.of(2000, 1, 1),
                null);

        assertThat(student).isNotNull();
    }

    @Test
    public void shouldThrowExceptionWhenNameIsBlank(){
        assertThatThrownBy(() -> new Student(UUID.randomUUID(),
                "",
                "12345678901",
                "joao@email.com",
                "graduado",
                address,
                LocalDate.of(2000, 1, 1),
                null))
                .isInstanceOf(StudentException.class)
                .hasMessageContaining(StudentErrorCode.INVALID_STUDENT.getMessageTemplate());
    }

    @Test
    public void shouldThrowExceptionWhenDocumentNumberIsBlank(){
        assertThatThrownBy(() -> new Student(UUID.randomUUID(),
                "Joao Silva",
                "",
                "joao@email.com",
                "graduado",
                address,
                LocalDate.of(2000, 1, 1),
                null))
                .isInstanceOf(StudentException.class)
                .hasMessageContaining(StudentErrorCode.INVALID_STUDENT.getMessageTemplate());
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsBlank(){
        assertThatThrownBy(() -> new Student(UUID.randomUUID(),
                "Joao Silva",
                "12345678901",
                "",
                "graduado",
                address,
                LocalDate.of(2000, 1, 1),
                null))
                .isInstanceOf(StudentException.class)
                .hasMessageContaining(StudentErrorCode.INVALID_STUDENT.getMessageTemplate());
    }

    @Test
    public void shouldThrowExceptionWhenAcademicLevelIsBlank(){
        assertThatThrownBy(() -> new Student(UUID.randomUUID(),
                "",
                "12345678901",
                "joao@email.com",
                "",
                address,
                LocalDate.of(2000, 1, 1),
                null))
                .isInstanceOf(StudentException.class)
                .hasMessageContaining(StudentErrorCode.INVALID_STUDENT.getMessageTemplate());
    }

    @Test
    public void shouldThrowExceptionWhenBirthDateIsNull(){
        assertThatThrownBy(() -> new Student(UUID.randomUUID(),
                "Joao Silva",
                "12345678901",
                "joao@email.com",
                "graduado",
                address,
                null,
                null))
                .isInstanceOf(StudentException.class)
                .hasMessageContaining(StudentErrorCode.INVALID_STUDENT.getMessageTemplate());
    }

    @Test
    public void shouldThrowExceptionWhenBirthDateIsInvalid(){
        assertThatThrownBy(() -> new Student(UUID.randomUUID(),
                "Joao Silva",
                "12345678901",
                "joao@email.com",
                "graduado",
                address,
                LocalDate.of(2030, 1, 1),
                null))
                .isInstanceOf(StudentException.class)
                .hasMessageContaining(StudentErrorCode.INVALID_STUDENT.getMessageTemplate());
    }

    @Test
    public void shouldThrowExceptionWhenAddressIsNull(){
        assertThatThrownBy(() -> new Student(UUID.randomUUID(),
                "Joao Silva",
                "12345678901",
                "joao@email.com",
                "graduado",
                null,
                LocalDate.of(2000, 1, 1),
                null))
                .isInstanceOf(StudentException.class)
                .hasMessageContaining(StudentErrorCode.INVALID_STUDENT_ADDRESS.getMessageTemplate());
    }
}
