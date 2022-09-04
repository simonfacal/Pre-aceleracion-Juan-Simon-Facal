package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.PeliculaFiltersDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PeliculaSpecification {

    public Specification<PeliculaEntity> getByFilters(PeliculaFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

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

            if (0 < filtersDTO.getGenre())
            {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("generoId"),
                                filtersDTO.getGenre()
                        )
                );
            }

            //remove duplicates
            query.distinct(true);

            //Order resolver
            String orderByField = "nombre";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))

            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
