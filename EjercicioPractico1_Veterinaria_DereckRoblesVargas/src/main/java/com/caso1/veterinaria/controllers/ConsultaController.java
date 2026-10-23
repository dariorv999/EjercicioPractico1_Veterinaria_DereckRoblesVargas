package com.caso1.veterinaria.controllers;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/consultas")
public class ConsultaController {

    private final MessageSource messageSource;

    @GetMapping("/formulario")
    public String formulario() {
        return "consultas/formulario";
    }

    @PostMapping("/enviar")
    public String enviar(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("todoOk", messageSource.getMessage("consulta.gracias", null, Locale.getDefault()));
        return "redirect:/consultas/formulario";
    }
}