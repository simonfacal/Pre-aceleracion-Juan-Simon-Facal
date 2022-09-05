package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.service.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private ICharacterService characterService;

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO personaje) {
        CharacterDTO characterSaved = characterService.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id )
    {
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO dto)
    {
        CharacterDTO characterUpdated= characterService.update(id,dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(characterUpdated);
    }


    @GetMapping("/all")
    public ResponseEntity<List<CharacterBasicDTO>> getAll() {
        List<CharacterBasicDTO> characters = characterService.getAll();
        return ResponseEntity.ok().body(characters);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id)
    {
        CharacterDTO character= characterService.getDetailsById(id);
        return ResponseEntity.ok(character);
    }
    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>>getDetailsByFilters(
            @RequestParam(required=false) String name,
            @RequestParam(required=false) Integer age,
            @RequestParam(required=false) Set<Long> movies
    )
    {
        List<CharacterBasicDTO>characters= characterService.getByFilters(name,age,movies);
        return ResponseEntity.ok(characters);
    }

}

