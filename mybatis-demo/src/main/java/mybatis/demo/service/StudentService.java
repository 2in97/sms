package mybatis.demo.service;

import java.util.List;

import mybatis.demo.entity.Page;
import mybatis.demo.entity.Student;

public interface StudentService {

	void addStudent(Student student);
	
	void editStudent(Student student);

	void deleteStudent(String uid);
	
	List<Student> sortByAge(int pageNum);
	
	void deleteAllStudents(String uid);

	Student getStudentById(String uid);

	List<Student> getAllStudent();
	
	Page getPage();

	List<Student> getStudentByPage(int pageNum, String sort, String search);

	void updatePhoto(String photo, String uid);

	void updateCV(String cv, String uid);

	void deleteCv(String uid);

}
