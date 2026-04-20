package br.com.simionato.aluno_service.domain.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

public class Validations {

    public static boolean checkBlankFields(Stream<String> fields) {
        return fields.anyMatch(StringUtils::isBlank);
    }
}
