package com.hmq.demo.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmq.demo.model.po.Course;
import com.hmq.demo.service.ICourseService;
import com.hmq.framework.controller.impl.GenController;

@RestController
@RequestMapping("/course")
public class CourseController extends GenController<Course, String, ICourseService> {
}
