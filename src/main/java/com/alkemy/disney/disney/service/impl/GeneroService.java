package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.mapper.GeneroMapper;
import com.alkemy.disney.disney.repository.IGeneroRepository;
import com.alkemy.disney.disney.service.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;

public class GeneroService implements IGeneroService {
    @Autowired
    private IGeneroRepository generoRepository;
    @Autowired
    private GeneroMapper generoMapper;

}
