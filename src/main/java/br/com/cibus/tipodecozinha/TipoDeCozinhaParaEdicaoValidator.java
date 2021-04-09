package br.com.cibus.tipodecozinha;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TipoDeCozinhaParaEdicaoValidator implements Validator {

    private TipoDeCozinhaRepository tipoDeCozinhaRepository;

    public TipoDeCozinhaParaEdicaoValidator(TipoDeCozinhaRepository tipoDeCozinhaRepository) {
        this.tipoDeCozinhaRepository = tipoDeCozinhaRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TipoDeCozinhaParaEdicaoForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TipoDeCozinhaParaEdicaoForm tipoDeCozinhaParaEdicaoForm = (TipoDeCozinhaParaEdicaoForm) target;

        if (tipoDeCozinhaRepository.existsByNomeAndIdNot(tipoDeCozinhaParaEdicaoForm.getNome(), tipoDeCozinhaParaEdicaoForm.getId())) {
            errors.rejectValue("nome", "nome.ja.existente", "Nome já existente");
        }
    }
}
