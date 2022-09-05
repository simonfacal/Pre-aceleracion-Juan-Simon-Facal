package com.alkemy.disney.disney.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="characters")
@Getter @Setter
@SQLDelete(sql="UPDATE characters SET deleted= true WHERE id=?")
@Where(clause="deleted=false")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private int age;
    private double weight;
    private String history;
    @ManyToMany(mappedBy = "characters")
    private List<MovieEntity> movies=new ArrayList<>();
    private boolean deleted=Boolean.FALSE;

}
