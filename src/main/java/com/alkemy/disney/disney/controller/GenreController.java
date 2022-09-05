package com.alkemy.disney.disney.controller;


import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

 @Autowired
    private IGenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll()
    {
        List<GenreDTO> genres= genreService.getAllGenres();
        return ResponseEntity.ok().body(genres);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genre)
    {
        GenreDTO genreSaved= genreService.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreSaved);
    }

}
