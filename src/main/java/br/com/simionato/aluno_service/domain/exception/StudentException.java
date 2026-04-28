package br.com.simionato.aluno_service.domain.exception;

import br.com.simionato.aluno_service.domain.exception.enums.StudentErrorCode;
import lombok.Getter;

@Getter
public class StudentException extends RuntimeException {

    private final String errorCode;

    public StudentException(StudentErrorCode studentErrorCode, Object... messageArgs) {
        super(studentErrorCode.formatMessage(messageArgs));
        this.errorCode = studentErrorCode.getCode();
    }
}
