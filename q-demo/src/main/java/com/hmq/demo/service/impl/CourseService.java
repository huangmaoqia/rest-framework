package com.hmq.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmq.demo.dao.ICourseDao;
import com.hmq.demo.model.po.Course;
import com.hmq.demo.service.ICourseService;
import com.hmq.framework.service.impl.GenService;

@Service
@Transactional
public class CourseService extends GenService<Course, String, ICourseDao>
		implements ICourseService {



}
