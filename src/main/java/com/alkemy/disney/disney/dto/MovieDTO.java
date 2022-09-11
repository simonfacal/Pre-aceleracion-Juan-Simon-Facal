package com.alkemy.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
public class MovieDTO {
    private Long id;
    private String image;
    @NotNull
    private String title;
    @Pattern(regexp = "\\d{4}\\d{2}\\d{2}",message = "the date format must be yyyy-MM-dd")
    private LocalDate creationDate;
    @Min(1)
    @Max(5)
    private int calification;
    private GenreDTO genre;
    private Long genreId;
    private List<@Valid CharacterDTO> characters =new ArrayList<>();
}
