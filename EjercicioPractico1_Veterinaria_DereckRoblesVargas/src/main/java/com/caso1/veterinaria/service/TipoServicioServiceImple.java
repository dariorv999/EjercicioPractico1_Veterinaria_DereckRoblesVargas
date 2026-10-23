package com.caso1.veterinaria.service;

import com.caso1.veterinaria.domain.TipoServicio;
import com.caso1.veterinaria.repository.TipoServicioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TipoServicioServiceImple implements TipoServicioService {

    private final TipoServicioRepository tipoServicioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicio> getTiposServicio() {
        return tipoServicioRepository.findAll();
    }

    @Override
    @Transactional
    public void save(TipoServicio tipoServicio) {
        tipoServicioRepository.save(tipoServicio);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!tipoServicioRepository.existsById(id)) {
            throw new IllegalArgumentException("El tipo de servicio con ID " + id + " no existe.");
        }
        try {
            tipoServicioRepository.deleteById(id);
            tipoServicioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el tipo de servicio. Tiene datos asociados.", e);
        }
    }
}

