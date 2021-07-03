package br.com.cibus.restaurante;

import br.com.cibus.exceptions.NotFoundException;
import br.com.cibus.tipodecozinha.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;
    private final TipoDeCozinhaRepository tipoDeCozinhaRepository;

    public RestauranteController(RestauranteRepository restauranteRepository, TipoDeCozinhaRepository tipoDeCozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.tipoDeCozinhaRepository = tipoDeCozinhaRepository;
    }

    @InitBinder("restauranteParaEdicaoForm")
    void initBinderEditaRestaurante(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new RestauranteParaEdicaoValidator(restauranteRepository));
    }

    @GetMapping("/restaurantes/editar/{slug}")
    String formularioEditar(@PathVariable String slug, Model model) {
        Restaurante restaurante = restauranteRepository.findBySlug(slug).orElseThrow(NotFoundException::new);
        List<TipoDeCozinha> tiposDeCozinha = tipoDeCozinhaRepository.findByOrderByNomeAsc();

        model.addAttribute("restaurante", restaurante);
        model.addAttribute("tiposDeCozinha", tiposDeCozinha);
        return "restaurante/formulario-editar";
    }

    @PostMapping("/restaurantes/editar/{slug}")
    String editar(@PathVariable String slug, @Valid RestauranteParaEdicaoForm restauranteParaEdicaoForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return formularioEditar(slug, model);
        }

        Restaurante restaurante = restauranteParaEdicaoForm.geraEntidade(restauranteRepository, tipoDeCozinhaRepository);
        restauranteRepository.save(restaurante);

        return "redirect:/restaurantes/editar/%s".formatted(slug);
    }
}
