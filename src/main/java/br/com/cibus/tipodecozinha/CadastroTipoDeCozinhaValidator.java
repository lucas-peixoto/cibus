package br.com.cibus.tipodecozinha;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CadastroTipoDeCozinhaValidator implements Validator {

    private TipoDeCozinhaRepository tipoDeCozinhaRepository;

    public CadastroTipoDeCozinhaValidator(TipoDeCozinhaRepository tipoDeCozinhaRepository) {
        this.tipoDeCozinhaRepository = tipoDeCozinhaRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TipoDeCozinhaForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TipoDeCozinhaForm tipoDeCozinhaForm = (TipoDeCozinhaForm) target;

        if (tipoDeCozinhaRepository.existsByNome(tipoDeCozinhaForm.getNome())) {
            errors.rejectValue("nome", "nome.ja.existente", "Nome já existente");
        }
     }
}
