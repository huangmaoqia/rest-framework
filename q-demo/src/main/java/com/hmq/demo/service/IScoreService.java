package com.hmq.demo.service;

import java.util.List;
import java.util.Map;

import com.hmq.demo.model.po.Score;
import com.hmq.demo.model.vo.ScoreVO;
import com.hmq.framework.service.IGenService;

public interface IScoreService extends IGenService<Score, String> {
	
	List<ScoreVO> findVOByFilter(Map<String, Object> filter, Integer pageIndex, Integer pageSize, String sortBy,
			String order);
	
}
