package com.hmq.demo.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmq.demo.model.po.Student;
import com.hmq.demo.service.IStudentService;
import com.hmq.framework.controller.impl.GenController;

@RestController
@RequestMapping("/student")
public class StudentController extends GenController<Student, String, IStudentService> {
}
