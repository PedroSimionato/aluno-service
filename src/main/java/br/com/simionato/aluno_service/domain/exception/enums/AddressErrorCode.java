package br.com.simionato.aluno_service.domain.exception.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Stream;

@Getter
public enum AddressErrorCode {

    INVALID_ADDRESS("INVALID_ADDRESS", "address fields cannot be empty", HttpStatus.NOT_ACCEPTABLE);

    private final String code;
    private final String messageTemplate;
    private final HttpStatus status;

    AddressErrorCode(String code, String messageTemplate, HttpStatus status){
        this.code = code;
        this.messageTemplate = messageTemplate;
        this.status = status;
    }

    public String fomatMessage(Object... args){
        return String.format(messageTemplate, args);
    }

    public static AddressErrorCode ofErrorCode(String errorCode){
        return Stream.of(values())
                .filter(value -> value.getCode().equals(errorCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid error code: " + errorCode));
    }
}
