package br.com.cibus.restaurante;

import br.com.cibus.exceptions.NotFoundException;
import br.com.cibus.tipodecozinha.TipoDeCozinha;
import br.com.cibus.tipodecozinha.TipoDeCozinhaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;
    private final TipoDeCozinhaRepository tipoDeCozinhaRepository;

    public RestauranteController(RestauranteRepository restauranteRepository, TipoDeCozinhaRepository tipoDeCozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.tipoDeCozinhaRepository = tipoDeCozinhaRepository;
    }

    @GetMapping("/restaurantes/editar/{slug}")
    String formularioEditar(@PathVariable String slug, Model model) {
        Restaurante restaurante = restauranteRepository.findBySlug(slug).orElseThrow(NotFoundException::new);
        List<TipoDeCozinha> tiposDeCozinha = tipoDeCozinhaRepository.findByOrderByNomeAsc();

        model.addAttribute("restaurante", restaurante);
        model.addAttribute("tiposDeCozinha", tiposDeCozinha);
        return "restaurante/formulario-editar";
    }
}
