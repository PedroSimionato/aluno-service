package br.com.simionato.aluno_service.domain;

import br.com.simionato.aluno_service.domain.exception.AcademicLevelException;
import br.com.simionato.aluno_service.domain.exception.enums.AcademicLevelErrorCode;

import java.util.stream.Stream;

public enum AcademicLevelEnum {
    HIGH_SCHOOL("HIGH SCHOOL", "HS"),
    GRADUATE("GRADUATE", "GR"),
    POST_GRADUATE("POST GRADUTE", "PG"),
    DOCTORATE("DOCTORATE","DC"),
    MASTER("MASTER", "MS");

    public String name;
    public String code;

    AcademicLevelEnum(String name, String code){
        this.name = name;
        this.code = code;
    }

    public static AcademicLevelEnum ofName(String levelName){
        return Stream.of(values())
                .filter(level -> level.name.equals(levelName.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new AcademicLevelException(AcademicLevelErrorCode.INVALID_ACADEMIC_LEVEL, levelName));
    }
}
