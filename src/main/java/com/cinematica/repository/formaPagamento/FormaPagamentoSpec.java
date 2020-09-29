package com.cinematica.repository.formaPagamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.cinematica.model.FormaPagamento;

public class FormaPagamentoSpec {

	public static Specification<FormaPagamento> searchName(FormaPagamentoFilterDTO filter) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (Objects.nonNull(filter.getDescricao()) && !filter.getDescricao().isEmpty()) {
				predicates.add(builder.like(builder.lower(root.<String>get("descricao")),
						"%".concat(filter.getDescricao().toLowerCase()).concat("%")));
			}
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
