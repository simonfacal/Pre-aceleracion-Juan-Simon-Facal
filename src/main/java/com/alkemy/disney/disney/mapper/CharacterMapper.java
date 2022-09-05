package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private IMovieRepository movieRepository;
    public CharacterEntity characterDTO2Entity(CharacterDTO dto)
    {
        CharacterEntity characterEntity =new CharacterEntity();

        characterEntity.setAge(dto.getAge());
        characterEntity.setHistory(dto.getHistory());
        characterEntity.setName(dto.getName());
        characterEntity.setImage(dto.getImage());
        characterEntity.setWeight(dto.getWeight());
        List<MovieEntity>movies=new ArrayList<>();
        for (int i=0;i<dto.getMovies().size();i++)
        {
            Optional<MovieEntity> movie=movieRepository.findById(dto.getMovies().get(i).getId());
            if(movie.isPresent())
                movies.add(movie.get());
        }
        characterEntity.setMovies(movies);

        return characterEntity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {
        CharacterDTO characterDTO = new CharacterDTO();

        characterDTO.setId(entity.getId());
        characterDTO.setAge(entity.getAge());
        characterDTO.setHistory(entity.getHistory());
        characterDTO.setName(entity.getName());
        characterDTO.setImage(entity.getImage());
        characterDTO.setWeight(entity.getWeight());
        if(loadMovies)
        {
            List<MovieDTO> movieDTOS =this.movieMapper.movieEntityList2DTOList(entity.getMovies(),false);
            characterDTO.setMovies(movieDTOS);
        }
        return characterDTO;
    }



    public void characterEntityRefreshValues(CharacterEntity entity, CharacterDTO characterDTO)
    {
        entity.setAge(characterDTO.getAge());
        entity.setHistory(characterDTO.getHistory());
        entity.setName(characterDTO.getName());
        entity.setWeight(characterDTO.getWeight());
        entity.setImage(characterDTO.getImage());

    }


    public Set<CharacterEntity> characterDTOList2Entity(List<CharacterDTO>dtos)
    {
        Set<CharacterEntity> entities=new HashSet<>();
        for (CharacterDTO dto: dtos)
        {
            entities.add(this.characterDTO2Entity(dto));
        }
        return entities;
    }

    public List<CharacterDTO> characterEntitySet2DTOList(Collection<CharacterEntity> entities, boolean loadMovies)
    {
        List<CharacterDTO> dtos=new ArrayList<>();
        for (CharacterEntity entity: entities)
        {
            dtos.add(this.characterEntity2DTO(entity,loadMovies));
        }
        return dtos;
    }



    public List<CharacterBasicDTO> characterEntitySet2BasicDTOList(Collection<CharacterEntity> entities)
    {
        List<CharacterBasicDTO> dtos=new ArrayList<>();
        CharacterBasicDTO basicDTO;
        for (CharacterEntity entity: entities)
        {
            basicDTO=new CharacterBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setImage(entity.getImage());
            basicDTO.setName(entity.getName());
            dtos.add(basicDTO);


        }
        return dtos;
    }


}
