package br.com.softplan.sienge.calculofrete.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculoFreteController {

    @GetMapping("/teste")
    public @ResponseBody ResponseEntity<String> teste() {
        return new ResponseEntity<String>("Oii", HttpStatus.OK);
    }

}
