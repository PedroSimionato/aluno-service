package br.com.simionato.aluno_service.domain.exception;

import br.com.simionato.aluno_service.domain.exception.enums.AcademicLevelErrorCode;
import lombok.Getter;

@Getter
public class AcademicLevelException extends RuntimeException {

    private String errorCode;

    public AcademicLevelException(AcademicLevelErrorCode academicLevelErrorCode, Object... messageArgs) {
        super(academicLevelErrorCode.formatMessage(messageArgs));
        this.errorCode = academicLevelErrorCode.getCode();
    }
}
