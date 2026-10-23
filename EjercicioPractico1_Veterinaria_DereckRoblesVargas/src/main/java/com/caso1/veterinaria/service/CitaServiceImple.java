package com.caso1.veterinaria.service;

import com.caso1.veterinaria.domain.Cita;
import com.caso1.veterinaria.repository.CitaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CitaServiceImple implements CitaService {

    private final CitaRepository citaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cita> getCitas() {
        return citaRepository.findAll();
    }
}

