package com.cinematica.repository.agenda;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.cinematica.model.Agenda;

public class AgendaSpec {

	public static Specification<Agenda> searchDesc(AgendaFilterDTO filter) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
