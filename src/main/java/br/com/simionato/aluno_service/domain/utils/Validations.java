package br.com.simionato.aluno_service.domain.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class Validations {

    public static boolean checkBlankFields(Stream<String> fields) {
        return fields.anyMatch(StringUtils::isBlank);
    }

    public static LocalDate parseStringToLocalDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
