package br.com.cibus.tipodecozinha;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/tipos-de-cozinha")
public class TipoDeCozinhaController {

    @GetMapping
    public String lista(){
        return "tipo-de-cozinha/listagem";
    }

    @GetMapping("/formulario-adicionar")
    public String formularioAdicionar(){
        return "tipo-de-cozinha/formulario-adicionar";
    }

    @PostMapping
    public String adiciona(@Valid TipoDeCozinhaForm tipoDeCozinhaForm, BindingResult bindingResult){
        System.out.println(tipoDeCozinhaForm.getNome());
        if(bindingResult.hasErrors()){
            System.out.println("erro");
            return "tipo-de-cozinha/formulario-adicionar";
        }
        return "tipo-de-cozinha/listagem";
    }

}
