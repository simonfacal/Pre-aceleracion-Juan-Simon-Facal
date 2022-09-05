package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName()))
            {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );

            }

            if ( filtersDTO.getGenre()!=null &&0 < filtersDTO.getGenre())
            {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("genreId"),
                                filtersDTO.getGenre()
                        )
                );
            }

            //remove duplicates
            query.distinct(true);

            //Order resolver
            String orderByField = "title";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))

            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
