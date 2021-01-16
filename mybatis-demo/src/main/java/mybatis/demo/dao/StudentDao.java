package mybatis.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import mybatis.demo.entity.Student;

@Mapper
public interface StudentDao {
	void editStudent(Student student);

	void deleteStudent(String uid);
	
	List<Student> sortByAge(int pageNum);
	
	void deleteAllStudents(String[] uids);

	Student getStudentById(String uid);

	List<Student> getAllStudent();

	List<Student> getStudentByPage(@Param("pageNum") int pageNum,@Param("sort") String sort,@Param("search") String search);

	void addStudent(Student student);

	void updatePhoto(@Param("photo") String photo,@Param("uid") String uid);

	void updateCV(@Param("cv") String cv,@Param("uid") String uid);

	void deleteCv(String uid);
}
