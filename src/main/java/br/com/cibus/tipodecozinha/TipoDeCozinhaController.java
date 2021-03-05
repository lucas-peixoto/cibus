package br.com.cibus.tipodecozinha;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/tipos-de-cozinha")
public class TipoDeCozinhaController {

    private TipoDeCozinhaRepository tipoDeCozinhaRepository;

    public TipoDeCozinhaController(TipoDeCozinhaRepository tipoDeCozinhaRepository) {
        this.tipoDeCozinhaRepository = tipoDeCozinhaRepository;
    }

    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new CadastroTipoDeCozinhaValidator(tipoDeCozinhaRepository));
    }

    @GetMapping
    public String lista(Model model){
        List<TipoDeCozinha> tiposDeCozinha = tipoDeCozinhaRepository.findAll();
        tiposDeCozinha.forEach(tc -> System.out.println(tc));
        model.addAttribute("tiposDeCozinha", tiposDeCozinha);
        return "tipo-de-cozinha/listagem";
    }

    @GetMapping("/novo")
    public String formularioAdicionar(){
        return "tipo-de-cozinha/formulario-adicionar";
    }

    @PostMapping("/novo")
    public String adiciona(@Valid TipoDeCozinhaForm tipoDeCozinhaForm, BindingResult bindingResult){
        System.out.println(tipoDeCozinhaForm.getNome());
        if(bindingResult.hasErrors()){
            System.out.println("erro");
            return "tipo-de-cozinha/formulario-adicionar";
        }

        TipoDeCozinha tipoDeCozinha = tipoDeCozinhaForm.toEntity();
        tipoDeCozinhaRepository.save(tipoDeCozinha);
        return "redirect:/admin/tipos-de-cozinha";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(){
        return "tipo-de-cozinha/formulario-editar";
    }


    @PostMapping("/editar/{id}")
    public String edita(@Valid TipoDeCozinhaForm tipoDeCozinhaForm, BindingResult bindingResult){
        return "redirect:/admin/tipos-de-cozinha";
    }
}
