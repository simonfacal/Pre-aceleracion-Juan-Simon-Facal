package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.entity.PeliculaEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.alkemy.disney.disney.dto.PersonajeFiltersDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

@Component
public class PersonajeSpecification {

    public Specification<PersonajeEntity> getByFilters(PersonajeFiltersDTO filtersDTO)
    {
        return(root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName()))
            {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );

            }

            if(0<filtersDTO.getAge())
            {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("edad"),
                        filtersDTO.getAge()
                        )
                );
            }


            if (!CollectionUtils.isEmpty(filtersDTO.getMovies()))
            {
                Join<PeliculaEntity, PersonajeEntity> join = root.join("peliculas", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
            }

            //remove duplicates
            query.distinct(true);


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
