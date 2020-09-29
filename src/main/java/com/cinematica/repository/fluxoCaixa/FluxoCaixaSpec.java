package com.cinematica.repository.fluxoCaixa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.cinematica.model.Especialidade;
import com.cinematica.model.SimNao;
import com.cinematica.repository.especialidade.EspecialidadeFilterDTO;

public class FluxoCaixaSpec {

	public static Specification<Especialidade> searchDesc(EspecialidadeFilterDTO filter) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (Objects.nonNull(filter.getDescricao()) && !filter.getDescricao().isEmpty()) {
				predicates.add(builder.like(builder.lower(root.<String>get("descricao")),
						"%".concat(filter.getDescricao().toLowerCase()).concat("%")));
			}
			predicates.add(builder.equal(root.<String>get("ativo"), SimNao.Sim));
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
