package com.hmq.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmq.demo.dao.IScoreDao;
import com.hmq.demo.model.po.Course;
import com.hmq.demo.model.po.Score;
import com.hmq.demo.model.po.Student;
import com.hmq.demo.model.vo.ScoreVO;
import com.hmq.demo.service.IScoreService;
import com.hmq.framework.service.impl.GenService;
import com.hmq.framework.utils.query.DataRelation;

@Service
@Transactional
public class ScoreService extends GenService<Score, String, IScoreDao>
		implements IScoreService {

	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseService courseService;
	
	@Override
	public List<ScoreVO> findVOByFilter(Map<String, Object> filter,Integer pageIndex, Integer pageSize, String sortBy,
			String order) {
		
		DataRelation<ScoreVO, Student> relationS2S= new DataRelation<ScoreVO, Student>(studentService);
		relationS2S.addForwardRelation(ScoreVO::getStudentId, Student::getId);
		relationS2S.addBackwardRelation(ScoreVO::setStudentName,Student::getStudentName);
		
		DataRelation<ScoreVO, Course> relationS2C = new DataRelation<ScoreVO, Course>(courseService);
		relationS2C.addForwardRelation(ScoreVO::getCourseId, Course::getId);
		relationS2C.addBackwardRelation(ScoreVO::setCourseName,Course::getCourseName);
		
		List<DataRelation<ScoreVO, ?>> relations=new ArrayList<>();
		relations.add(relationS2S);
		relations.add(relationS2C);
		
		return this.findVOByFilter(filter, pageIndex, pageSize, sortBy, order, relations, ScoreVO.class);
	}
   
	

}
