package br.com.cibus.geral.validacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidatorContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CnpjValidatorTest {

    CnpjValidator cnpjValidator;
    ConstraintValidatorContext context;

    String cnpjValido1 = "44309402000148";
    String cnpjValido2 = "02454938000107";
    String cnpjInvalido1 = "3457623827364";
    String cnpjInvalido2 = "22222222222222";
    String cnpjInvalido3 = "23453489573894";

    @BeforeEach
    void beforeEach() {
        cnpjValidator = new CnpjValidator();
        context = mock(ConstraintValidatorContext.class);
    }

    @Test
    void isValidIdentificaSeUmCNPJEValido() {
        cnpjValidator = new CnpjValidator();

        boolean isValid = cnpjValidator.isValid(cnpjValido1, context);
        assertThat(isValid).isTrue();

        isValid = cnpjValidator.isValid(cnpjValido2, context);
        assertThat(isValid).isTrue();
    }

    @Test
    void isValidIdentificaSeUmCNPJEInvalido() {
        boolean isValid = cnpjValidator.isValid(cnpjInvalido1, context);
        assertThat(isValid).isFalse();

        isValid = cnpjValidator.isValid(cnpjInvalido2, context);
        assertThat(isValid).isFalse();

        isValid = cnpjValidator.isValid(cnpjInvalido3, context);
        assertThat(isValid).isFalse();
    }
}