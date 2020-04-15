package com.hmq.framework.utis;

import java.io.Serializable;

public class IdUtil {
	@SuppressWarnings("unchecked")
	public  static <ID extends Serializable> ID genID(){
		return (ID) UUIDUtil.newUUID();
	}
}
