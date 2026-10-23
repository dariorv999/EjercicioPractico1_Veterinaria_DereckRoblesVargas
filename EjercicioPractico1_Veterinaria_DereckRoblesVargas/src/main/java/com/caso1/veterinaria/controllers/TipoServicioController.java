package com.caso1.veterinaria.controllers;

import com.caso1.veterinaria.domain.TipoServicio;
import com.caso1.veterinaria.service.TipoServicioService;
import jakarta.validation.Valid;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tiposervicio")
public class TipoServicioController {

    private final TipoServicioService tipoServicioService;
    private final MessageSource messageSource;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("tiposServicio", tipoServicioService.getTiposServicio());
        model.addAttribute("tipoServicio", new TipoServicio());
        return "tiposervicio/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid TipoServicio tipoServicio, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String mensaje = tipoServicio.getId() == null ? "mensaje.agregado" : "mensaje.actualizado";
        if (bindingResult.hasErrors()) {
            titulo = "error";
            mensaje = "tiposervicio.error04";
        } else {
            try {
                tipoServicioService.save(tipoServicio);
            } catch (DataIntegrityViolationException e) {
                titulo = "error";
                mensaje = "tiposervicio.error04";
            }
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(mensaje, null, Locale.getDefault()));
        return "redirect:/tiposervicio/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idTipoServicio, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            tipoServicioService.delete(idTipoServicio);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            detalle = "tiposervicio.error01";
        } catch (IllegalStateException e) {
            titulo = "error";
            detalle = "tiposervicio.error02";
        } catch (Exception e) {
            titulo = "error";
            detalle = "tiposervicio.error03";
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/tiposervicio/listado";
    }
}
