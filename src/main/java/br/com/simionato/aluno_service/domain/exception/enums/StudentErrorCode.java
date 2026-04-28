package br.com.simionato.aluno_service.domain.exception.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum StudentErrorCode {

    INVALID_STUDENT("INVALID_STUDENT", "student fields cannot be empty", HttpStatus.BAD_REQUEST),
    INVALID_STUDENT_ADDRESS("INVALID_STUDENT", "student addres cannot be null", HttpStatus.BAD_REQUEST),
    INVALID_BIRTHDATE("INVALID_BRITHDATE", "invalid student birthdate", HttpStatus.BAD_REQUEST),
    STUDENT_ALREADY_EXISTS("STUDENT_ALREADY_EXISTS", "student with document number %s already exists", HttpStatus.NOT_ACCEPTABLE),
    STUDENT_NOT_FOUND("STUDENT_NOT_FOUND", "student with identifier %s not found", HttpStatus.NOT_FOUND);

    private final String code;
    private final String messageTemplate;
    private final HttpStatus status;

    StudentErrorCode(String code, String messageTemplate, HttpStatus status) {
        this.code = code;
        this.messageTemplate = messageTemplate;
        this.status = status;
    }

    public String formatMessage(Object... args) {
        return String.format(messageTemplate, args);
    }

    public static StudentErrorCode ofErrorCode(String errorCode){

        return Arrays.stream(values())
                .filter(value -> value.getCode().equals(errorCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid error code: " + errorCode));
    }
}
