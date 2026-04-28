package br.com.simionato.aluno_service.domain.model;

import br.com.simionato.aluno_service.domain.exception.AddressException;
import br.com.simionato.aluno_service.domain.exception.enums.AddressErrorCode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AddressTest {

    @Test
    public void shouldCreateAddressWhenAllFieldsAreValid(){
        Address address = new Address("Rua Itu",
                "415",
                "Casa 2",
                "Baeta Neves",
                "Sao Bernardo do Campo",
                "Sao Paulo",
                "09751040");

        assertThat(address).isNotNull();
    }

    @Test
    public void shouldCreateAddressWhenComplementIsEmpty(){
        Address address = new Address("Rua Itu",
                "415",
                "",
                "Baeta Neves",
                "Sao Bernardo do Campo",
                "Sao Paulo",
                "09751040");

        assertThat(address).isNotNull();
    }

    @Test
    public void shouldCreateAddressWhenComplementIsNull(){
        Address address = new Address("Rua Itu",
                "415",
                "",
                "Baeta Neves",
                "Sao Bernardo do Campo",
                "Sao Paulo",
                "09751040");

        assertThat(address).isNotNull();
    }

    @Test
    public void shouldThrowExceptionWhenStreetIsEmpty(){
        assertThatThrownBy(() -> new Address("",
                "415",
                "Casa 2",
                "Baeta Neves",
                "Sao Bernardo do Campo",
                "Sao Paulo",
                "09751040"))
        .isInstanceOf(AddressException.class)
        .hasMessageContaining(AddressErrorCode.INVALID_ADDRESS.getMessageTemplate());
    }

    @Test
    public void shouldThrowExceptionWhenNumberIsEmpty(){
        assertThatThrownBy(() -> new Address("Rua Itu",
                "",
                "Casa 2",
                "Baeta Neves",
                "Sao Bernardo do Campo",
                "Sao Paulo",
                "09751040"))
                .isInstanceOf(AddressException.class)
                .hasMessageContaining(AddressErrorCode.INVALID_ADDRESS.getMessageTemplate());
    }

    @Test
    public void shouldThrowExceptionWhenNeighborhoodIsEmpty(){
        assertThatThrownBy(() -> new Address("Rua Itu",
                "415",
                "Casa 2",
                "",
                "Sao Bernardo do Campo",
                "Sao Paulo",
                "09751040"))
                .isInstanceOf(AddressException.class)
                .hasMessageContaining(AddressErrorCode.INVALID_ADDRESS.getMessageTemplate());
    }

    @Test
    public void shouldThrowExceptionWhenCityIsEmpty(){
        assertThatThrownBy(() -> new Address("Rua Itu",
                "415",
                "Casa 2",
                "Baeta Neves",
                "",
                "Sao Paulo",
                "09751040"))
                .isInstanceOf(AddressException.class)
                .hasMessageContaining(AddressErrorCode.INVALID_ADDRESS.getMessageTemplate());
    }

    @Test
    public void shouldThrowExceptionWhenStateIsEmpty(){
        assertThatThrownBy(() -> new Address("Rua Itu",
                "415",
                "Casa 2",
                "Baeta Neves",
                "Sao Bernardo do Campo",
                "",
                "09751040"))
                .isInstanceOf(AddressException.class)
                .hasMessageContaining(AddressErrorCode.INVALID_ADDRESS.getMessageTemplate());
    }

    @Test
    public void shouldThrowExceptionWhenZipcodeIsEmpty(){
        assertThatThrownBy(() -> new Address("Rua Itu",
                "415",
                "Casa 2",
                "Baeta Neves",
                "Sao Bernardo do Campo",
                "Sao Paulo",
                ""))
                .isInstanceOf(AddressException.class)
                .hasMessageContaining(AddressErrorCode.INVALID_ADDRESS.getMessageTemplate());
    }
}
