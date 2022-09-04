package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class PeliculaController {

    @Autowired
    private IPeliculaService peliculaService;

    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO pelicula) {
        PeliculaDTO peliculaGuardada = peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);

    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Void> addPersonaje(@PathVariable Long idMovie,@PathVariable Long idCharacter)
    {
        peliculaService.addCharacter(idMovie,idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Void> removePersonaje(@PathVariable Long idMovie,@PathVariable Long idCharacter )
    {
        peliculaService.removeCharacter(idMovie,idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> update(@PathVariable Long id,@RequestBody PeliculaDTO dto)
    {
        PeliculaDTO peliculaActualizada=peliculaService.update(id,dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(peliculaActualizada);
    }

    @GetMapping()
    public ResponseEntity<List<PeliculaBasicDTO>> getAll()
    {
        List<PeliculaBasicDTO> peliculas=peliculaService.getAll();
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping()
    public ResponseEntity<List<PeliculaBasicDTO>>getDetailsByFilters(
            @RequestParam(required=false) String name,
            @RequestParam(required=false) Long genre,
            @RequestParam(required=false) String order
        )
    {
        List<PeliculaBasicDTO>peliculas=peliculaService.getByFilters(name,genre,order);
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO>getDetailsById(@PathVariable Long id)
    {
        PeliculaDTO pelicula=peliculaService.getDetailsById(id);
        return ResponseEntity.ok(pelicula);
    }

}
