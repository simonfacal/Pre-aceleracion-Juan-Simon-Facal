package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.repository.IPeliculaRepository;
import com.alkemy.disney.disney.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;

public class PeliculaService implements IPeliculaService {
    @Autowired
    private IPeliculaRepository peliculaRepository;
    @Autowired
    private PeliculaMapper peliculaMapper;
}
