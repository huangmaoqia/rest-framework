package com.hmq.demo.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmq.demo.model.po.BillDetail;
import com.hmq.demo.service.IBillDetailService;
import com.hmq.framework.controller.impl.GenController;

@RestController
@RequestMapping("/billDetail")
public class BillDetailController extends GenController<BillDetail, String, IBillDetailService> {
}
