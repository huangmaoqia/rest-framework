package com.hmq.framework.utils.query;

import com.hmq.framework.utils.query.IGetter;

public class ForwardRelation<S, T> {
	private IGetter<S> sGetter=null;
	private IGetter<T> tGetter=null;
	
	public ForwardRelation(IGetter<S> sGetter,IGetter<T> tGetter){
		this.sGetter=sGetter;
		this.tGetter=tGetter;
	}

	public IGetter<S> getSGetter() {
		return sGetter;
	}

	public IGetter<T> getTGetter() {
		return tGetter;
	}
}
