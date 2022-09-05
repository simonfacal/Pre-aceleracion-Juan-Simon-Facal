package com.alkemy.disney.disney.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="genre")
@Getter @Setter
@SQLDelete(sql="UPDATE genre SET deleted= true WHERE id=?")
@Where(clause="deleted=false")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private boolean deleted=Boolean.FALSE;

}
