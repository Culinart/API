package culinart.utils;

import culinart.domain.receita.Receita;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ReceitaSpecifications {

    public static Specification<Receita> porNomeOuIngredienteOuCategoria(String termo) {
        return (Root<Receita> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (termo != null && !termo.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("nome"), "%" + termo + "%"));

                // Join com Ingrediente
                root.join("ingredientes", JoinType.LEFT); // Mude para JoinType.INNER se desejar apenas receitas que têm ingredientes

                predicates.add(criteriaBuilder.like(root.get("ingredientes").get("nome"), "%" + termo + "%"));

                // Join com Categoria
                root.join("receitaCategorias", JoinType.LEFT);

                predicates.add(criteriaBuilder.like(root.get("receitaCategorias").get("categoria").get("nome"), "%" + termo + "%"));
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
