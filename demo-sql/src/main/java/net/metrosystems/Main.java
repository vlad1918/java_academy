package net.metrosystems;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	StudentRepository studentRepository;
	
	@RequestMapping(path="jdbc")
	public List<Student> jdbcExample() {
		

		//Execute an SQL statement that returns void
		jdbcTemplate.execute("update student set age=99 where id=1");
		
		//Execute an SQL statement, return the number of affected rows
		int nbRows = jdbcTemplate.update("update student set age=88 where id=1");
				
		//Query the database
		List<Student> students = jdbcTemplate.query("select * from student",
				new StudentRowMapper());
		
		return students;

	}
	
	
	@RequestMapping(path="jpa")
	public List<Student> jpaExample() {
		
		List<Student> students = new ArrayList<>();

		//Update example
		Student student = studentRepository.findOne(1);
		student.setName("Jean Jenescu");
		student.setAge(22);
		studentRepository.save(student);
		
		//Insert example
		Student newStudent = new Student();
		newStudent.setAge(44);
		newStudent.setGender("MALE");
		newStudent.setName("Ionel Ionescu");
		studentRepository.save(newStudent);
		
		//Select all
		studentRepository.findAll().forEach(s -> students.add(s)); 
		
		return students;
	}
}
