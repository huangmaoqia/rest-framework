package com.hmq.framework.utils.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public final class PageUtil {
	
	private static final Integer PAGESIZE=10; 
	private static final String ORDER="DESC";

	public static Sort buildSort(String sortBy, String order) {
		if (order == null) {
			order = ORDER;
		}
		String[] sortByArr = sortBy.split(",");
		String[] orederArr = order.split(",");
		List<Order> orderList = new ArrayList<Order>();
		if (sortByArr.length != orederArr.length) {
			String[] _orderArr=new String[sortByArr.length];
			for (int i = 0; i < sortByArr.length; ++i) {
				if(i<orederArr.length) {
					_orderArr[i]=orederArr[i];
				}else {
					_orderArr[i]=orederArr[0];
				}
			}
			orederArr=_orderArr;
		} else {
			for (int i = 0; i < sortByArr.length; ++i) {
				Order o = new Order("asc".equalsIgnoreCase(orederArr[i]) ? Direction.ASC : Direction.DESC, sortByArr[i]);
				orderList.add(o);
			}
		}
		Sort pageSort = new Sort(orderList);
		return pageSort;
	}

	public static Pageable buildPageable(Integer pageIndex, Integer pageSize, String sortBy, String order) {
		Pageable pageable = null;
		if (pageSize == null) {
			pageSize = PAGESIZE;
		}
		
		if (sortBy != null) {
			Sort pageSort = buildSort(sortBy, order);
			pageable = new PageRequest(pageIndex-1, pageSize, pageSort);
		} else {
			pageable = new PageRequest(pageIndex-1, pageSize);
		}
		return pageable;
	}
}
