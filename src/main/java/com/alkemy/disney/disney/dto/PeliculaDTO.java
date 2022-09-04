package com.alkemy.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
public class PeliculaDTO {
    private Long id;
    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
    private int calificacion;
    private GeneroDTO genero;
    private Long generoId;
    private List<PersonajeDTO> personajes=new ArrayList<>();
}
