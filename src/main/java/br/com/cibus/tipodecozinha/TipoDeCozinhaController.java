package br.com.cibus.tipodecozinha;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TipoDeCozinhaController {

    @GetMapping("/ola")
    public String alo(){
        return "tipo-de-cozinha/listagem";
    }
}
