package br.com.cibus.restaurante;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RestauranteParaEdicaoValidator implements Validator {

    private RestauranteRepository restauranteRepository;

    public RestauranteParaEdicaoValidator(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RestauranteParaEdicaoForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RestauranteParaEdicaoForm restauranteParaEdicaoForm = (RestauranteParaEdicaoForm) target;

        if (restauranteRepository.existsByNomeAndIdNot(restauranteParaEdicaoForm.getNome(), restauranteParaEdicaoForm.getId())) {
            errors.rejectValue("nome", "nome.ja.existente", "Nome já existente");
        }
    }
}
