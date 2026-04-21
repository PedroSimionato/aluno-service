package br.com.simionato.aluno_service.domain.exception.enums;

import lombok.Getter;

@Getter
public enum StudentErrorCode {

    INVALID_STUDENT("INVALID_STUDENT", "student fields cannot be empty"),
    INVALID_STUDENT_ADDRESS("INVALID_STUDENT", "student addres cannot be null"),
    INVALID_BIRTHDATE("INVALID_BRITHDATE", "invalid student birthdate"),
    STUDENT_ALREADY_EXISTS("STUDENT_ALREADY_EXISTS", "student with document number %s already exists"),
    STUDENT_NOT_FOUND("STUDENT_NOT_FOUND", "student with identifier %s not found");

    private final String code;
    private final String messageTemplate;

    StudentErrorCode(String code, String messageTemplate) {
        this.code = code;
        this.messageTemplate = messageTemplate;
    }

    public String formatMessage(Object... args) {
        return String.format(messageTemplate, args);
    }
}
