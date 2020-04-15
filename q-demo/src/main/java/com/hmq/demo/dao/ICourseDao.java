package com.hmq.demo.dao;

import org.springframework.stereotype.Repository;

import com.hmq.demo.model.po.Course;
import com.hmq.framework.dao.IGenDao;

@Repository
public interface ICourseDao extends IGenDao<Course, String> {

}