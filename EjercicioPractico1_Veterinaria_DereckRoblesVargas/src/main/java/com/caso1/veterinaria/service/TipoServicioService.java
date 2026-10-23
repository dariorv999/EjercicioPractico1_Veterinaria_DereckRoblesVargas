package com.caso1.veterinaria.service;

import com.caso1.veterinaria.domain.TipoServicio;
import java.util.List;

public interface TipoServicioService {

    public List<TipoServicio> getTiposServicio();

    public void save(TipoServicio tipoServicio);

    public void delete(Integer id);
}
