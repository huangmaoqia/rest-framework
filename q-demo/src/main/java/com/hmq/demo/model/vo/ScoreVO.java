package com.hmq.demo.model.vo;

import com.hmq.demo.model.po.Score;

public class ScoreVO extends Score{
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	String studentName;
	String courseName;
}
