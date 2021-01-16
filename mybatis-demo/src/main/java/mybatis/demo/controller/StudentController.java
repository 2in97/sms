package mybatis.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mybatis.demo.entity.Page;
import mybatis.demo.entity.Student;
import mybatis.demo.service.StudentService;


@RestController
@CrossOrigin(origins = "*",allowCredentials = "true")
@RequestMapping("/test")
public class StudentController {

	@Autowired
	private StudentService service;
	@PostMapping("/editStudent")
	public void editStudent(@RequestBody Student student) {
		service.editStudent(student);
	}
	
	@PostMapping("/addStudent")
	public void addStudent(@RequestBody Student student) {
		service.addStudent(student);
	}
	
	@GetMapping("/deleteStudent")
	public void deleteStudent(@RequestParam String uid) {
		service.deleteStudent(uid);
	}
	@GetMapping("/deleteCv")
	public void deleteCv(@RequestParam String uid) {
		service.deleteCv(uid);
	}
	@GetMapping("/sortByAge")
	public List<Student> sortByAge(@RequestParam int pageNum) {
		return service.sortByAge(pageNum);
	}
	@GetMapping("/deleteAllStudents")
	public void deleteAllStudents(@RequestParam String uid) {
		service.deleteAllStudents(uid);
	}

	@GetMapping("/getStudentById")
	public Student getStudentById(@RequestParam String uid) {
		return service.getStudentById(uid);
	}

	@GetMapping("/getAllStudent")
	public List<Student> getAllStudent() {
		return service.getAllStudent();
	}

	@GetMapping("/getPage")
	public Page getPage() {
		return service.getPage();
	}

	@GetMapping("/getStudents")
	public List<Student> getStudentByPage(@RequestParam int pageNum,@RequestParam String sort,@RequestParam String search) {
		return service.getStudentByPage(pageNum,sort,search);
	}
}
