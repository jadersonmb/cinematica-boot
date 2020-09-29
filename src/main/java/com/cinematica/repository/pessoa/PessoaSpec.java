package com.cinematica.repository.pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.cinematica.model.Pessoa;
import com.cinematica.model.SimNao;

public class PessoaSpec {

	public static Specification<Pessoa> searchName(PessoaFilterDTO filter) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (Objects.nonNull(filter.getNome()) && !filter.getNome().isEmpty()) {
				predicates.add(builder.like(builder.lower(root.<String>get("nome")),
						"%".concat(filter.getNome().toLowerCase()).concat("%")));
			}
			predicates.add(builder.equal(root.<String>get("ativo"), SimNao.Sim));
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
