package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filtersDTO)
    {
        return(root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName()))
            {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );

            }

            if(filtersDTO.getAge()!=null && 0<filtersDTO.getAge())
            {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("age"),
                        filtersDTO.getAge()
                        )
                );
            }


            if (!CollectionUtils.isEmpty(filtersDTO.getMovies()))
            {
                Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
            }

            //remove duplicates
            query.distinct(true);


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
