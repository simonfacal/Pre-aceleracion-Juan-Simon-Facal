package com.alkemy.disney.disney.mapper;



import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.repository.IGeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class PeliculaMapper {

    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private IGeneroRepository generoRepository;


    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto) {
        PeliculaEntity peliculaEntity = new PeliculaEntity();
        peliculaEntity.setCalificacion(dto.getCalificacion());
        peliculaEntity.setTitulo(dto.getTitulo());
        peliculaEntity.setImagen(dto.getImagen());
        peliculaEntity.setFechaCreacion(dto.getFechaCreacion());
        Set<PersonajeEntity> personajes=personajeMapper.personajeDTOList2Entity(dto.getPersonajes());
        peliculaEntity.setPersonajes(personajes);
        peliculaEntity.setGeneroId(dto.getGeneroId());
        Optional<GeneroEntity> genero=generoRepository.findById(dto.getGeneroId());
        peliculaEntity.setGenero(genero.get());
        return peliculaEntity;
    }

    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity, boolean loadPersonajes) {
        PeliculaDTO peliculaDTO = new PeliculaDTO();

        peliculaDTO.setId(entity.getId());
        peliculaDTO.setCalificacion(entity.getCalificacion());
        peliculaDTO.setTitulo(entity.getTitulo());
        peliculaDTO.setImagen(entity.getImagen());
        peliculaDTO.setFechaCreacion(entity.getFechaCreacion());
        if (loadPersonajes) {
            List<PersonajeDTO> personajeDTOS = this.personajeMapper.personajeEntitySet2DTOList(entity.getPersonajes(), false);
            peliculaDTO.setPersonajes(personajeDTOS);
        }

        return peliculaDTO;
    }


    public void PeliculaEntityRefreshValues(PeliculaEntity entity, PeliculaDTO peliculaDTO) {
        entity.setCalificacion(peliculaDTO.getCalificacion());
        entity.setTitulo(peliculaDTO.getTitulo());
        entity.setImagen(peliculaDTO.getImagen());
        //no se si debo setear el id de genero
        entity.setGeneroId(peliculaDTO.getGeneroId());

        entity.setFechaCreacion(peliculaDTO.getFechaCreacion());

    }

    public List<PeliculaEntity> peliculaDTOList2Entity(List<PeliculaDTO> dtos) {
        List<PeliculaEntity> entities = new ArrayList<>();
        for (PeliculaDTO dto : dtos) {
            entities.add(this.peliculaDTO2Entity(dto));
        }
        return entities;
    }

    public List<PeliculaDTO> peliculaEntityList2DTOList(List<PeliculaEntity> entities, boolean loadPersonajes) {
        List<PeliculaDTO> dtos = new ArrayList<>();
        for (PeliculaEntity entity : entities) {
            dtos.add(this.peliculaEntity2DTO(entity, loadPersonajes));
        }

        return dtos;

    }

    public List<PeliculaBasicDTO> peliculaEntityList2BasicDTOList(List<PeliculaEntity> entities)
    {
        List<PeliculaBasicDTO>dtos=new ArrayList<>();
        PeliculaBasicDTO basicDTO;
        for(PeliculaEntity entity:entities)
        {
            basicDTO=new PeliculaBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setTitulo(entity.getTitulo());
            basicDTO.setImagen(entity.getImagen());
            basicDTO.setFechaCreacion(entity.getFechaCreacion());
            dtos.add(basicDTO);
        }
        return dtos;
    }


}
