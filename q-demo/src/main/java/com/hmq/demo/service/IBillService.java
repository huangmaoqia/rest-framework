package com.hmq.demo.service;

import java.util.List;
import java.util.Map;

import com.hmq.demo.model.po.Bill;
import com.hmq.demo.model.vo.BillVO;
import com.hmq.framework.service.IGenService;

public interface IBillService extends IGenService<Bill, String> {
	List<BillVO> findVOByFilter(Map<String, Object> filter,Integer pageIndex, Integer pageSize, String sortBy,
			String order);
}
