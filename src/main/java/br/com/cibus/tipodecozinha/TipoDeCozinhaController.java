package br.com.cibus.tipodecozinha;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
