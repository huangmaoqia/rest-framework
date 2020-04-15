package com.hmq.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmq.demo.dao.IStudentDao;
import com.hmq.demo.model.po.Student;
import com.hmq.demo.service.IStudentService;
import com.hmq.framework.service.impl.GenService;
import com.hmq.framework.utils.query.Conditions;

@Service
@Transactional
public class StudentService extends GenService<Student, String, IStudentDao>
		implements IStudentService {

	//演示对JpaSpecificationExecutor进行封装
	//Conditions是Specification的继承类
	public void findUser(){
		Conditions<Student> conditions=new Conditions<>();
		conditions.addCdLt(Student::getAge, 18);
		conditions.addCdLike(Student::getStudentName, "红");
		
		//按条件查询
		List<Student> list1= this.findBySpec(conditions);
		//排序
		List<Student> list2= this.findBySpec(conditions, "age", "decs");
		//分页加排序
		List<Student> list3= this.findBySpec(conditions, 1,10,"age", "decs");
		//获取数量
		long count=this.countBySpec(conditions);
	}

}
