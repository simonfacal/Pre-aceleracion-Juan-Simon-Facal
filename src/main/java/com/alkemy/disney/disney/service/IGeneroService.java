package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GeneroDTO;

import java.util.List;

public interface IGeneroService {

    public GeneroDTO save(GeneroDTO dto);

    public List<GeneroDTO> getAllGeneros();

}
