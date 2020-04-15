package com.hmq.framework.utils.query;

import org.springframework.data.jpa.domain.Specification;

public class AndConditions<T> extends Conditions<T> implements Specification<T> {

	public AndConditions(){
		super(ELogicalOperator.AND);
	}
}
