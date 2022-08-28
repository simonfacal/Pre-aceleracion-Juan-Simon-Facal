package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pelicula")
@Getter @Setter
public class PeliculaEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String imagen;
    private String titulo;
    @Column(name="fecha_creacion")
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private LocalDate fechaCreacion;
    private int calificacion;
    @ManyToMany(
            cascade= {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name="personaje_pelicula",
            joinColumns = @JoinColumn(name="pelicula_id"),
            inverseJoinColumns = @JoinColumn(name="personaje_id"))

    private Set<PersonajeEntity> personajes=new HashSet<>();

}
