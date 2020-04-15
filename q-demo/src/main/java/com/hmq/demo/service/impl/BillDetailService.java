package com.hmq.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmq.demo.dao.IBillDetailDao;
import com.hmq.demo.model.po.BillDetail;
import com.hmq.demo.service.IBillDetailService;
import com.hmq.framework.service.impl.GenService;

@Service
@Transactional
public class BillDetailService extends GenService<BillDetail, String, IBillDetailDao>
		implements IBillDetailService {



}
