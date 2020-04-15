package com.hmq.demo.controller.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmq.demo.model.po.Bill;
import com.hmq.demo.model.vo.BillVO;
import com.hmq.demo.service.IBillService;
import com.hmq.framework.controller.impl.GenController;

@RestController
@RequestMapping("/bill")
public class BillController extends GenController<Bill, String, IBillService> {
	@GetMapping("/vo")
	public List<BillVO> serachVO(HttpServletRequest request, Integer pageIndex, Integer pageSize, String sortBy,
			String order) {
		Map<String, Object> filter = getParams(request);
		List<BillVO> voList = this.getService().findVOByFilter(filter, pageIndex, pageSize, sortBy, order);
		return voList;
	}
}
