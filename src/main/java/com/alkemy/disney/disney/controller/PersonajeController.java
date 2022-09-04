package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.service.IPersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class PersonajeController {

    @Autowired
    private IPersonajeService personajeService;

    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje) {
        PersonajeDTO personajeGuardado = personajeService.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id )
    {
        personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> update(@PathVariable Long id, @RequestBody PersonajeDTO dto)
    {
        PersonajeDTO personajeActualizado=personajeService.update(id,dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personajeActualizado);
    }


    @GetMapping
    public ResponseEntity<List<PersonajeBasicDTO>> getAll() {
        List<PersonajeBasicDTO> personajes = personajeService.getAll();
        return ResponseEntity.ok().body(personajes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getDetailsById(@PathVariable Long id)
    {
        PersonajeDTO personaje=personajeService.getDetailsById(id);
        return ResponseEntity.ok(personaje);
    }
    @GetMapping
    public ResponseEntity<List<PersonajeBasicDTO>>getDetailsByFilters(
            @RequestParam(required=false) String name,
            @RequestParam(required=false) int age,
            @RequestParam(required=false) Set<Long> movies
    )
    {
        List<PersonajeBasicDTO>personajes=personajeService.getByFilters(name,age,movies);
        return ResponseEntity.ok(personajes);
    }

}

