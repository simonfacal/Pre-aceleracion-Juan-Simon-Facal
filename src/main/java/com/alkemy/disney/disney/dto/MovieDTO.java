package com.alkemy.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
public class MovieDTO {
    private Long id;
    private String image;
    private String title;
    private LocalDate creationDate;
    private int calification;
    private GenreDTO genre;
    private Long genreId;
    private List<CharacterDTO> characters =new ArrayList<>();
}
