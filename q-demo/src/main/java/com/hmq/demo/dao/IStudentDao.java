package com.hmq.demo.dao;

import org.springframework.stereotype.Repository;

import com.hmq.demo.model.po.Student;
import com.hmq.framework.dao.IGenDao;

@Repository
public interface IStudentDao extends IGenDao<Student, String> {

}