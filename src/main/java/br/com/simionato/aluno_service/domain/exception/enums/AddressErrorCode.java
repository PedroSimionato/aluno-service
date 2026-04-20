package br.com.simionato.aluno_service.domain.exception.enums;

import lombok.Getter;

@Getter
public enum AddressErrorCode {

    INVALID_ADDRESS("INVALID_ADDRESS", "address fields cannot be empty");

    private final String code;
    private final String messageTemplate;

    AddressErrorCode(String code, String messageTemplate){
        this.code = code;
        this.messageTemplate = messageTemplate;
    }

    public String fomatMessage(Object... args){
        return String.format(messageTemplate, args);
    }
}
