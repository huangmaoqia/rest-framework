package com.hmq.framework.utils.query;

import com.hmq.framework.utils.query.IGetter;
import com.hmq.framework.utils.query.ISetter;

public class BackwardRelation<S, T> {
	private ISetter<S, ?> sSetter=null;
	private IGetter<T> tGetter=null;
	
	public BackwardRelation(ISetter<S, ?> sSetter, IGetter<T> tGetter) {
		super();
		this.sSetter = sSetter;
		this.tGetter = tGetter;
	}
	
	
	public ISetter<S, ?> getSSetter() {
		return sSetter;
	}
	public IGetter<T> getTGetter() {
		return tGetter;
	}
}
