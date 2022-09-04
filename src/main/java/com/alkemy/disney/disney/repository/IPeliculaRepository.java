package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.PeliculaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPeliculaRepository extends JpaRepository<PeliculaEntity,Long> {
    List<PeliculaEntity>findAll(Specification<PeliculaEntity>spec);
}
