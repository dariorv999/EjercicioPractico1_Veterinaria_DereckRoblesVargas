
package com.caso1.veterinaria.controllers;

import com.caso1.veterinaria.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cita")
public class CitaController {

    private final CitaService citaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("citas", citaService.getCitas());
        return "cita/listado";
    }
}
