package br.com.simionato.aluno_service.domain.model;

import br.com.simionato.aluno_service.domain.exception.AddressException;
import br.com.simionato.aluno_service.domain.exception.enums.AddressErrorCode;
import lombok.Getter;

import java.util.stream.Stream;

import static br.com.simionato.aluno_service.domain.utils.Validations.checkBlankFields;

@Getter
public class Address {
    private final String street;
    private final String number;
    private final String complement;
    private final String neighborhood;
    private final String city;
    private final String state;
    private final String zipcode;

    public Address(String street, String number, String complement, String neighborhood, String city, String state, String zipcode) {
        checkFields(street, number, neighborhood, city, state, zipcode);

        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    private static void checkFields(String street, String number, String neighborhood, String city, String state, String zipcode) {
        boolean fieldsInvalid = checkBlankFields(Stream.of(
                street,
                number,
                neighborhood,
                city,
                state,
                zipcode
        ));

        if (fieldsInvalid) {
            throw new AddressException(AddressErrorCode.INVALID_ADDRESS);
        }
    }
}
