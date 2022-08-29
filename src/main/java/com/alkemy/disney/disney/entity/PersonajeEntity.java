package com.alkemy.disney.disney.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="personaje")
@Getter @Setter
@SQLDelete(sql="UPDATE icon SET deleted= true WHERE id=?")
@Where(clause="deleted=false")
public class PersonajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagen;
    private String nombre;
    private int edad;
    private double peso;
    private String historia;
    @ManyToMany(mappedBy = "personajes", cascade=CascadeType.ALL)
    private List<PeliculaEntity> peliculas=new ArrayList<>();
    private boolean deleted=Boolean.FALSE;

}
