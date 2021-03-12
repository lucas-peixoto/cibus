package br.com.cibus.tipodecozinha;

import br.com.cibus.exceptions.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TipoDeCozinhaController {

    private TipoDeCozinhaRepository tipoDeCozinhaRepository;

    public TipoDeCozinhaController(TipoDeCozinhaRepository tipoDeCozinhaRepository) {
        this.tipoDeCozinhaRepository = tipoDeCozinhaRepository;
    }

    @InitBinder("tipoDeCozinhaForm")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new CadastroTipoDeCozinhaValidator(tipoDeCozinhaRepository));
    }

    @InitBinder("tipoDeCozinhaParaEdicaoForm")
    void initBinderEditaCozinha(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new TipoDeCozinhaParaEdicaoValidator(tipoDeCozinhaRepository));
    }

    @GetMapping("/admin/tipos-de-cozinha")
    public String lista(Model model) {
        List<TipoDeCozinha> tiposDeCozinha = tipoDeCozinhaRepository.findByOrderByNomeAsc();
        tiposDeCozinha.forEach(tc -> System.out.println(tc));
        model.addAttribute("tiposDeCozinha", tiposDeCozinha);
        return "tipo-de-cozinha/listagem";
    }

    @GetMapping("/admin/tipos-de-cozinha/novo")
    public String formularioAdicionar() {
        return "tipo-de-cozinha/formulario-adicionar";
    }

    @PostMapping("/admin/tipos-de-cozinha/novo")
    public String adiciona(@Valid TipoDeCozinhaForm tipoDeCozinhaForm, BindingResult bindingResult) {
        System.out.println(tipoDeCozinhaForm.getNome());
        if (bindingResult.hasErrors()) {
            System.out.println("erro");
            return "tipo-de-cozinha/formulario-adicionar";
        }

        TipoDeCozinha tipoDeCozinha = tipoDeCozinhaForm.toEntity();
        tipoDeCozinhaRepository.save(tipoDeCozinha);
        return "redirect:/admin/tipos-de-cozinha";
    }

    @GetMapping("/admin/tipos-de-cozinha/editar/{id}")
    public String formularioEditar(@PathVariable("id") Long id, Model model) {
        TipoDeCozinha tipoDeCozinha = tipoDeCozinhaRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("tipoDeCozinha", tipoDeCozinha);
        return "tipo-de-cozinha/formulario-editar";
    }

    @PostMapping("/admin/tipos-de-cozinha/editar")
    public String edita(@Valid TipoDeCozinhaParaEdicaoForm tipoDeCozinhaForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tipo-de-cozinha/formulario-editar";
        }

        TipoDeCozinha tipoDeCozinha = tipoDeCozinhaForm.toEntity(tipoDeCozinhaRepository);
        tipoDeCozinhaRepository.save(tipoDeCozinha);

        return "redirect:/admin/tipos-de-cozinha";
    }

    @PostMapping("/admin/tipos-de-cozinha/remover/{id}")
    public String remover(@PathVariable("id") Long id) {
        tipoDeCozinhaRepository.deleteById(id);
        return "redirect:/admin/tipos-de-cozinha";
    }
}
