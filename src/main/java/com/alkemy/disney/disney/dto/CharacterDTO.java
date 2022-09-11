package com.alkemy.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CharacterDTO {
    private Long id;
    private String image;
    @NotNull
    private String name;
    @Positive
    private Integer age;
    private double weight;
    private String history;
    private List<MovieDTO> movies =new ArrayList<>();
}
