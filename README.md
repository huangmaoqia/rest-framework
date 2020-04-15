#### 基于SpringBoot实现一个rest代码框架

- 0行代码实现增删查改
>GET /student:列出所有学生<br>
GET /student/ID:获取某个学生<br>
POST /student:新建/更新一个学生<br>
DELETE /student/ID:删除某个学生

- 0行代码实现单表大部分查询功能
>//支持EQ(等于), NE(不等于), LIKE, GT(大于), LT(小于), GTE(大于等于), LTE(小于等于), IN<br>
//获取所有学生<br>
GET http://www.hmq123.cn:8081/student<br>
>//获取id为1的学生<br>
GET http://www.hmq123.cn:8081/student?id=1<br>
//查询年龄大于30并且名字模糊匹配"%红%"的学生<br>
GET http://www.hmq123.cn:8081/student?age-GT=30&studentName-LIKE=红<br>
//查询年龄在29,30,31中的学生<br>
GET http://www.hmq123.cn:8081/student?age-IN=29,30,31<br>
//分页查询<br>
GET http://www.hmq123.cn:8081/student?pageIndex=1&pageSize=10&sortBy=age&order=asc<br>
//日期类型字段正则处理,匹配多种日期格式:年,年月,年月日,年月日时,年月日时分,年月日时分秒<br>
GET http://www.hmq123.cn:8081/student?birthday-GT=1990<br>
GET http://www.hmq123.cn:8081/student?birthday-GT=1990-02-01<br>

- 10行代码实现表关联查询,并且关联字段自动变成可查询字段
> //有成绩表,学生表,课程表<br>
//Score--courseId-id--Course<br>
//Score--studentId-id--Student<br>
//查询课程名(Course表字段)为语文,并且分数(Score表字段)大于等于95的成绩<br>
GET http://www.hmq123.cn:8081/score/vo?score-GTE=95&courseName=语文<br>
```
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
```
- 6行代码实现主从关联
> //有订单表,订单详情表<br>
//Bill--id-billId--BillDetail<br>
//BillDetail作为从表一起被加载<br>
GET http://www.hmq123.cn:8081/bill/vo
```
@Override
public List<BillVO> findVOByFilter(Map<String, Object> filter,Integer pageIndex, Integer pageSize, String sortBy,
		String order) {
	
	DataRelation<BillVO, BillDetail> relation = new DataRelation<>(billDetailService);
	relation.addForwardRelation(BillVO::getId, BillDetail::getBillId);
	relation.addBackwardRelation(BillVO::setBillDetailList,null);
	
	List<DataRelation<BillVO, ?>> relations=new ArrayList<>();
	relations.add(relation);
	
	return this.findVOByFilter(filter, pageIndex, pageSize, sortBy, order, relations, BillVO.class);
}
```
