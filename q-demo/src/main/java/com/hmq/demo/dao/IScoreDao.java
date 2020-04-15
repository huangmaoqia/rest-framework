package com.hmq.demo.dao;

import org.springframework.stereotype.Repository;

import com.hmq.demo.model.po.Score;
import com.hmq.framework.dao.IGenDao;

@Repository
public interface IScoreDao extends IGenDao<Score, String> {

}