package com.alkemy.disney.disney.controller;


import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.service.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GeneroController {

 @Autowired
    private IGeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll()
    {
        List<GeneroDTO> generos=generoService.getAllGeneros();
        return ResponseEntity.ok().body(generos);
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO genero)
    {
        GeneroDTO generoGuardado= generoService.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }

}
