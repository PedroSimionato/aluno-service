package br.com.simionato.aluno_service.adapters.in.web.exception;

import br.com.simionato.aluno_service.domain.exception.AcademicLevelException;
import br.com.simionato.aluno_service.domain.exception.AddressException;
import br.com.simionato.aluno_service.domain.exception.StudentException;
import br.com.simionato.aluno_service.domain.exception.enums.AcademicLevelErrorCode;
import br.com.simionato.aluno_service.domain.exception.enums.AddressErrorCode;
import br.com.simionato.aluno_service.domain.exception.enums.StudentErrorCode;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String STUDENT_ERROR_TITLE = "Student Error";
    private static final String ADDRESS_ERROR_TITLE = "Address Error";
    private static final String ACADEMIC_LEVEL_ERROR_TITLE = "Academic Level Error";
    private static final String ERROR_CODE_PROPERTY = "errorCode";
    private static final String TIMESTAMP_PROPERTY = "timestamp";

    @ExceptionHandler(StudentException.class)
    public ProblemDetail handleStudentException(StudentException ex) {
        log.error("Student error: {} - code: {}", ex.getMessage(), ex.getErrorCode());
        StudentErrorCode studentErrorCode = StudentErrorCode.ofErrorCode(ex.getErrorCode());

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                studentErrorCode.getStatus(),
                ex.getMessage()
        );

        problem.setTitle(STUDENT_ERROR_TITLE);
        problem.setProperty(ERROR_CODE_PROPERTY, ex.getErrorCode());
        problem.setProperty(TIMESTAMP_PROPERTY, Instant.now());

        return problem;
    }

    @ExceptionHandler(AddressException.class)
    public ProblemDetail handleAddressException(AddressException ex) {
        log.error("Address error : {} - code {}", ex.getMessage(), ex.getErrorCode());
        AddressErrorCode addressErrorCode = AddressErrorCode.ofErrorCode(ex.getErrorCode());

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                addressErrorCode.getStatus(),
                ex.getMessage()
        );

        problem.setTitle(ADDRESS_ERROR_TITLE);
        problem.setProperty(ERROR_CODE_PROPERTY, ex.getErrorCode());
        problem.setProperty(TIMESTAMP_PROPERTY, Instant.now());

        return problem;
    }

    @ExceptionHandler(AcademicLevelException.class)
    public ProblemDetail handleAcademicLevelException(AcademicLevelException ex) {
        log.error("Academic level error : {} - code {}", ex.getMessage(), ex.getErrorCode());
        AcademicLevelErrorCode addressErrorCode = AcademicLevelErrorCode.ofErrorCode(ex.getErrorCode());

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                addressErrorCode.getStatus(),
                ex.getMessage()
        );

        problem.setTitle(ACADEMIC_LEVEL_ERROR_TITLE);
        problem.setProperty(ERROR_CODE_PROPERTY, ex.getErrorCode());
        problem.setProperty(TIMESTAMP_PROPERTY, Instant.now());

        return problem;
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ProblemDetail handleCallNotPermittedException(CallNotPermittedException ex){
        log.warn("Circuit breaker is open: {}", ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.SERVICE_UNAVAILABLE,
                HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()
        );

        problem.setTitle("Circuit breaker is open");
        problem.setProperty(TIMESTAMP_PROPERTY, Instant.now());

        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
        );

        problem.setTitle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        problem.setProperty(TIMESTAMP_PROPERTY, Instant.now());

        return problem;
    }

}
