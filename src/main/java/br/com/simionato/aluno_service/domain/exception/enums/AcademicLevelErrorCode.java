package br.com.simionato.aluno_service.domain.exception.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

@Getter
public enum AcademicLevelErrorCode {
    INVALID_ACADEMIC_LEVEL("INVALID_ACADEMIC_LEVEL", "academic level %s does not exists", HttpStatus.NOT_ACCEPTABLE);

    private final String code;
    private final String messageTemplate;
    private final HttpStatus status;

    AcademicLevelErrorCode(String code, String messageTemplate, HttpStatus status){
        this.code = code;
        this.messageTemplate = messageTemplate;
        this.status = status;
    }

    public String formatMessage(Object... args){
        return String.format(messageTemplate, args);
    }

    public static AcademicLevelErrorCode ofErrorCode(String errorCode){
        return Stream.of(values())
                .filter(value -> value.getCode().equals(errorCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid error code: " + errorCode));
    }
}
