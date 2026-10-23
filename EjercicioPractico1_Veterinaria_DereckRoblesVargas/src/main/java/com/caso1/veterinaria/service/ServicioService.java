
package com.caso1.veterinaria.service;

import com.caso1.veterinaria.domain.Servicio;
import java.util.List;

public interface ServicioService {

    public List<Servicio> getServicios();

    public void save(Servicio servicio);

    public void delete(Integer id);
}
