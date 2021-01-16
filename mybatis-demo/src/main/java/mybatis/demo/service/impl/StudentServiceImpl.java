package mybatis.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mybatis.demo.dao.StudentDao;
import mybatis.demo.entity.Page;
import mybatis.demo.entity.Student;
import mybatis.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
	@Autowired
	private StudentDao dao;
	private static Page page = new Page();
	private Student stu=new Student();
	@Override
	public void editStudent(Student student) {
		stu=dao.getStudentById(student.getUid());
		dao.editStudent(student);
		LOGGER.info("把"+stu.toString()+"修改为"+student.toString());
	};
	@Override
	public void updatePhoto(String photo,String uid) {
		LOGGER.info("更新了："+uid+"的头像");
		dao.updatePhoto(photo,uid);
	};
	@Override
	public void updateCV(String cv, String uid) {
		LOGGER.info("更新了："+uid+"的简历");
		dao.updateCV(cv,uid);
	}
	@Override
	public void deleteCv(String uid) {
		LOGGER.info("删除了："+uid+"的简历");
		dao.deleteCv(uid);
	}
	@Override
	public void addStudent(Student student) {
		LOGGER.info("新增了："+student.toString());
		dao.addStudent(student);
	};
	@Override
	public void deleteStudent(String uid) {
		LOGGER.info("删除了："+uid);
		dao.deleteStudent(uid);
	};
	@Override
	public List<Student> sortByAge(int pageNum) {
		if(page.getRecordsNum()==-1)
			getPage();
		page.setPageNum(pageNum);
		return dao.sortByAge(pageNum);
	};
	@Override
	public void deleteAllStudents(String uid) {
		String[] uids = uid.split(","); // 分割成数组
		LOGGER.info("删除了："+uid.toString());
		dao.deleteAllStudents(uids);
	};
	@Override
	public Student getStudentById(String uid) {
		return dao.getStudentById(uid);
	};
	@Override
	public List<Student> getAllStudent(){
		return dao.getAllStudent();
	};
	@Override
	public Page getPage() {
		page.setRecordsNum(dao.getAllStudent());
		page.setPageTotal();
		return page;
	};
	@Override
	public List<Student> getStudentByPage(int pageNum, String sort, String search){
		if(page.getRecordsNum()==-1)
			getPage();
		page.setPageNum(pageNum);
		return dao.getStudentByPage(pageNum,sort,search);
	}
}
