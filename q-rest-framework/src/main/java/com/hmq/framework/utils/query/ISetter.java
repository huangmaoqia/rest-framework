package com.hmq.framework.utils.query;

import java.io.Serializable;

@FunctionalInterface
public interface ISetter<T, V> extends Serializable {
    void apply(T target,V value);
}
