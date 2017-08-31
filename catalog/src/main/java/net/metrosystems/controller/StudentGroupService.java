package net.metrosystems.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.metrosystems.domain.Course;
import net.metrosystems.domain.Grade;
import net.metrosystems.domain.Student;
import net.metrosystems.domain.StudentGroup;
import net.metrosystems.repository.GradeRepository;
import net.metrosystems.repository.StudentGroupRepository;

@Service
@Transactional
public class StudentGroupService {

	@Autowired
	StudentGroupRepository studentGroupRepository;
	@Autowired
	GradeRepository gradeRepository;
	
	public List<StudentGroup> findStudentGroupWithStudentsAndCourses() {
		List<StudentGroup> studentGroups = new ArrayList<>();
		//Force eager loading of students, courses and grades from the student group
		studentGroupRepository.findAll().forEach(studentGroup -> {
			studentGroup.getCourses().size();
			studentGroup.getCourses().forEach(course -> course.getGrades().size());
			studentGroup.getStudents().size();
			studentGroups.add(studentGroup);
		});
		
		return studentGroups;
	}	
	
	public Grade saveGrade(Course course, Student student, int value, Date date) {
		Grade grade = new Grade();
		grade.setCourse(course);
		grade.setStudent(student);
		grade.setValue(value);
		grade.setDate(date);
		
		return gradeRepository.save(grade);
	}
}
