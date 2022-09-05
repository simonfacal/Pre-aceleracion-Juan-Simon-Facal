package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    public GenreDTO save(GenreDTO dto);

    public List<GenreDTO> getAllGenres();

}
