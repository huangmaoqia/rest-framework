package com.hmq.framework.utils.query;

import org.springframework.data.jpa.domain.Specification;

public class OrConditions<T> extends Conditions<T> implements Specification<T> {

	public OrConditions(){
		super(ELogicalOperator.OR);
	}
}
