package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.mapper.GenreMapper;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.IGenreRepository;
import com.alkemy.disney.disney.repository.IMovieRepository;
import com.alkemy.disney.disney.repository.ICharacterRepository;
import com.alkemy.disney.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.disney.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepository movieRepository;
    @Autowired
    private ICharacterRepository characterRepository;

    @Autowired
    private IGenreRepository genreRepository;

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private MovieSpecification movieSpecification;


    @Override
    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity= movieMapper.movieDTO2Entity(dto);
        MovieEntity entitySaved= movieRepository.save(entity);
        List<CharacterDTO>characters=characterMapper.characterEntitySet2DTOList(entitySaved.getCharacters(),true);
        //a cada personaje le añado la pelicula que acabo de crear y lo updateo
        for (int i=0;i<characters.size();i++)
        {
            this.addCharacter(entitySaved.getId(),characters.get(i).getId());
        }
        MovieDTO result= movieMapper.movieEntity2DTO(entitySaved,true);
        return result;
    }
    @Override
    public MovieDTO update(Long id, MovieDTO dto) {
        Optional<MovieEntity> movie = movieRepository.findById(id);
        if (!movie.isPresent())
            throw new ParamNotFound("Id movie no valido");
        MovieEntity movieSaved = movie.get();

        Optional<GenreEntity> genero= genreRepository.findById(dto.getGenreId());
        if(genero.isPresent())
            movieSaved.setGenre(genero.get());
        movieSaved.setCalification(dto.getCalification());
        movieSaved.setTitle(dto.getTitle());
        movieSaved.setImage(dto.getImage());
        movieSaved.setCreationDate(dto.getCreationDate());
        movieSaved.setGenreId(dto.getGenreId());
        movieRepository.save(movieSaved);
        MovieDTO result = movieMapper.movieEntity2DTO(movieSaved, true);
        return result;
    }
    @Override
    public void delete(Long id) {

        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieBasicDTO> getAll() {
        List<MovieEntity>entities= movieRepository.findAll();
        List<MovieBasicDTO>result= movieMapper.movieEntityList2BasicDTOList(entities);

        return result;
    }
    @Override
    public MovieDTO getDetailsById(Long id) {
        Optional<MovieEntity> movie= movieRepository.findById(id);
        if(!movie.isPresent())
            throw new ParamNotFound("Id movie no valido");
        MovieDTO result= movieMapper.movieEntity2DTO(movie.get(),true);
        return result;
    }

    @Override
    public List<MovieBasicDTO> getByFilters(String name, Long genre, String order) {

        MovieFiltersDTO filtersDTO=new MovieFiltersDTO(name,genre,order);
        List<MovieEntity> entities= movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieBasicDTO>dtos= movieMapper.movieEntityList2BasicDTOList(entities);
        return dtos;

    }

    @Override
    public void addCharacter(Long idMovie, Long idCharacter) {
        Optional<MovieEntity> movie= movieRepository.findById(idMovie);
        Optional<CharacterEntity> character= characterRepository.findById(idCharacter);
        if (!movie.isPresent() && !character.isPresent())
            throw new ParamNotFound("Id movie e Id character no validos");
        else
            if(!movie.isPresent())
                throw new ParamNotFound("Id movie no valido");
            else
                if(!character.isPresent())
                    throw new ParamNotFound("Id character no valido");
    //si la pelicula no contiene al personaje, lo añade
       if (!movie.get().getCharacters().contains(character.get()))
       {
           movie.get().getCharacters().add(character.get());
           movieRepository.save(movie.get());
       }
       character.get().getMovies().add(movie.get());
       characterRepository.save(character.get());

    }

    @Override
    public void removeCharacter(Long idMovie, Long idCharacter) {
        Optional<MovieEntity> movie= movieRepository.findById(idMovie);
        Optional<CharacterEntity> character= characterRepository.findById(idCharacter);
        if (!movie.isPresent() && !character.isPresent())
            throw new ParamNotFound("Id movie e Id character no validos");
        else
            if(!movie.isPresent())
                throw new ParamNotFound("Id movie no valido");
            else
                if(!character.isPresent())
                    throw new ParamNotFound("Id character no valido");
        movie.get().getCharacters().remove(character.get());
        character.get().getMovies().remove(movie.get());
        characterRepository.save(character.get());
        movieRepository.save(movie.get());

    }
}
