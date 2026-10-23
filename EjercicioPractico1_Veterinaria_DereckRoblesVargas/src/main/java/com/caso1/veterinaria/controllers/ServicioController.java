package com.caso1.veterinaria.controllers;

import com.caso1.veterinaria.domain.Servicio;
import com.caso1.veterinaria.domain.TipoServicio;
import com.caso1.veterinaria.service.ServicioService;
import com.caso1.veterinaria.service.TipoServicioService;
import jakarta.validation.Valid;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/servicio")
public class ServicioController {

    private final ServicioService servicioService;
    private final TipoServicioService tipoServicioService;
    private final MessageSource messageSource;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("servicios", servicioService.getServicios());
        model.addAttribute("tiposServicio", tipoServicioService.getTiposServicio());
        var servicio = new Servicio();
        servicio.setTipoServicio(new TipoServicio());
        model.addAttribute("servicio", servicio);
        return "servicio/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Servicio servicio, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String mensaje = servicio.getId() == null ? "mensaje.agregado" : "mensaje.actualizado";
        if (bindingResult.hasErrors()) {
            titulo = "error";
            mensaje = "servicio.error04";
        } else {
            try {
                servicioService.save(servicio);
            } catch (DataIntegrityViolationException e) {
                titulo = "error";
                mensaje = "servicio.error04";
            }
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(mensaje, null, Locale.getDefault()));
        return "redirect:/servicio/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idServicio, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            servicioService.delete(idServicio);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            detalle = "servicio.error01";
        } catch (IllegalStateException e) {
            titulo = "error";
            detalle = "servicio.error02";
        } catch (Exception e) {
            titulo = "error";
            detalle = "servicio.error03";
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/servicio/listado";
    }
}

