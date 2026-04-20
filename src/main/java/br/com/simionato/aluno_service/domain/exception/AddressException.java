package br.com.simionato.aluno_service.domain.exception;

import br.com.simionato.aluno_service.domain.exception.enums.AddressErrorCode;
import lombok.Getter;

@Getter
public class AddressException extends RuntimeException{

    private final String errorCode;

    public AddressException(AddressErrorCode addressErrorCode, Object... messageArgs){
        super(addressErrorCode.fomatMessage(messageArgs));
        this.errorCode = addressErrorCode.getCode();
    }
}
