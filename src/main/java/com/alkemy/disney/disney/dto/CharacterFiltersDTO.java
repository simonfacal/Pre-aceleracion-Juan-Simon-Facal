package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class CharacterFiltersDTO {

    private String name;
    private int age;
    private Set<Long> movies;

    public CharacterFiltersDTO(String name, int age, Set<Long> movies) {
        this.name = name;
        this.age = age;
        this.movies = movies;
    }
}
