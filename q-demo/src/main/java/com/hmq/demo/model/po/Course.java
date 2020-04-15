package com.hmq.demo.model.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hmq.framework.model.GenPO;

@Entity
@Table(name = "t_course")
public class Course extends GenPO<String> {

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	String courseName;

}