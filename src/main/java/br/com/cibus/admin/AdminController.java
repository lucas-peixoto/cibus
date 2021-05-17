package br.com.cibus.admin;

import br.com.cibus.tipodecozinha.RestaurantesPorTipoDeCozinha;
import br.com.cibus.tipodecozinha.TipoDeCozinhaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private TipoDeCozinhaRepository tipoDeCozinhaRepository;

    public AdminController(TipoDeCozinhaRepository tipoDeCozinhaRepository) {
        this.tipoDeCozinhaRepository = tipoDeCozinhaRepository;
    }

    @GetMapping("/admin")
    String dashboard(Model model) {
        List<RestaurantesPorTipoDeCozinha> restaurantesPorTipoDeCozinha = tipoDeCozinhaRepository.contaRestaurantesPorTipoDeCozinha();

        model.addAttribute("restaurantesPorTipoDeCozinha", restaurantesPorTipoDeCozinha);

        return "admin/dashboard";
    }
}
