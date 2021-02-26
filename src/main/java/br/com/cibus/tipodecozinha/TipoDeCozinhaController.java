package br.com.cibus.tipodecozinha;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TipoDeCozinhaController {

    @GetMapping("/ola")
    @ResponseBody
    public String alo(){
        return "Ola";
    }
}
