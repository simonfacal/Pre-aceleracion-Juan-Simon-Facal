package com.alkemy.disney.disney.mapper;



import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.repository.ICharacterRepository;
import com.alkemy.disney.disney.repository.IGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private IGenreRepository genreRepository;
    @Autowired
    private ICharacterRepository characterRepository;
    @Autowired
    private GenreMapper genreMapper;


    public MovieEntity movieDTO2Entity(MovieDTO dto) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setCalification(dto.getCalification());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setImage(dto.getImage());
        movieEntity.setCreationDate(dto.getCreationDate());
        Set<CharacterEntity>characters=characterMapper.characterDTOList2Entity(dto.getCharacters());
        movieEntity.setCharacters(characters);
        movieEntity.setGenreId(dto.getGenreId());
        Optional<GenreEntity> genero= genreRepository.findById(dto.getGenreId());
        if(genero.isPresent())
            movieEntity.setGenre(genero.get());
        return movieEntity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters) {
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setId(entity.getId());
        movieDTO.setCalification(entity.getCalification());
        movieDTO.setTitle(entity.getTitle());
        movieDTO.setImage(entity.getImage());
        movieDTO.setGenreId(entity.getGenreId());
        movieDTO.setGenre(genreMapper.generoEntity2DTO(entity.getGenre()));
        movieDTO.setCreationDate(entity.getCreationDate());
        if (loadCharacters) {
            List<CharacterDTO> characterDTOS = this.characterMapper.characterEntitySet2DTOList(entity.getCharacters(), false);
            movieDTO.setCharacters(characterDTOS);
        }

        return movieDTO;
    }


    public void movieEntityRefreshValues(MovieEntity entity, MovieDTO movieDTO) {
        entity.setCalification(movieDTO.getCalification());
        entity.setTitle(movieDTO.getTitle());
        entity.setImage(movieDTO.getImage());
        //no se si debo setear el id de genero
        entity.setGenreId(movieDTO.getGenreId());

        entity.setCreationDate(movieDTO.getCreationDate());

    }

    public List<MovieEntity> movieDTOList2Entity(List<MovieDTO> dtos) {
        List<MovieEntity> entities = new ArrayList<>();
        for (MovieDTO dto : dtos) {
            entities.add(this.movieDTO2Entity(dto));
        }
        return entities;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtos.add(this.movieEntity2DTO(entity, loadCharacters));
        }

        return dtos;

    }

    public List<MovieBasicDTO> movieEntityList2BasicDTOList(List<MovieEntity> entities)
    {
        List<MovieBasicDTO>dtos=new ArrayList<>();
        MovieBasicDTO basicDTO;
        for(MovieEntity entity:entities)
        {
            basicDTO=new MovieBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setTitle(entity.getTitle());
            basicDTO.setImage(entity.getImage());
            basicDTO.setCreationDate(entity.getCreationDate());
            dtos.add(basicDTO);
        }
        return dtos;
    }


}
