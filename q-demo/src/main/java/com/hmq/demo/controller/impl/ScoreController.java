package com.hmq.demo.controller.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmq.demo.model.po.Score;
import com.hmq.demo.model.vo.ScoreVO;
import com.hmq.demo.service.IScoreService;
import com.hmq.framework.controller.impl.GenController;

@RestController
@RequestMapping("/score")
public class ScoreController extends GenController<Score, String, IScoreService> {
	
	@GetMapping("/vo")
	public List<ScoreVO> serachVO(HttpServletRequest request, Integer pageIndex, Integer pageSize, String sortBy,
			String order) {
		Map<String, Object> filter = getParams(request);
		List<ScoreVO> voList = this.getService().findVOByFilter(filter, pageIndex, pageSize, sortBy, order);
		return voList;
	}
}
