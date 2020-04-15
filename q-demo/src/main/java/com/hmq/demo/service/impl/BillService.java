package com.hmq.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmq.demo.dao.IBillDao;
import com.hmq.demo.model.po.Bill;
import com.hmq.demo.model.po.BillDetail;
import com.hmq.demo.model.vo.BillVO;
import com.hmq.demo.service.IBillService;
import com.hmq.framework.service.impl.GenService;
import com.hmq.framework.utils.query.DataRelation;

@Service
@Transactional
public class BillService extends GenService<Bill, String, IBillDao> implements IBillService {

	@Autowired
	BillDetailService billDetailService;
	
	@Override
	public List<BillVO> findVOByFilter(Map<String, Object> filter,Integer pageIndex, Integer pageSize, String sortBy,
			String order) {
		
		DataRelation<BillVO, BillDetail> relation = new DataRelation<>(billDetailService);
		relation.addForwardRelation(BillVO::getId, BillDetail::getBillId);
		relation.addBackwardRelation(BillVO::setBillDetailList,null);
		
		List<DataRelation<BillVO, ?>> relations=new ArrayList<>();
		relations.add(relation);
		
		return this.findVOByFilter(filter, pageIndex, pageSize, sortBy, order, relations, BillVO.class);
	}
}
