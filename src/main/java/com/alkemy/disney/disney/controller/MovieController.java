package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) {
        MovieDTO movieSaved = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);

    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter)
    {
        movieService.addCharacter(idMovie,idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Void> removePersonaje(@PathVariable Long idMovie,@PathVariable Long idCharacter )
    {
        movieService.removeCharacter(idMovie,idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
         movieService.delete(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO dto)
    {
        MovieDTO movieUpdated= movieService.update(id,dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(movieUpdated);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieBasicDTO>> getAll()
    {
        List<MovieBasicDTO> movies= movieService.getAll();
        return ResponseEntity.ok(movies);
    }

    @GetMapping()
    public ResponseEntity<List<MovieBasicDTO>>getDetailsByFilters(
            @RequestParam(required=false) String name,
            @RequestParam(required=false) Long genre,
            @RequestParam(required=false,defaultValue = "ASC") String order
        )
    {
        List<MovieBasicDTO>movies= movieService.getByFilters(name,genre,order);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO>getDetailsById(@PathVariable Long id)
    {
        MovieDTO movie= movieService.getDetailsById(id);
        return ResponseEntity.ok(movie);
    }

}
