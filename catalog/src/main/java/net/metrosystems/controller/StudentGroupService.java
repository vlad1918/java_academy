package net.metrosystems.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.metrosystems.domain.Course;
import net.metrosystems.domain.Grade;
import net.metrosystems.domain.StudentGroup;
import net.metrosystems.repository.StudentGroupRepository;

@Service
@Transactional
public class StudentGroupService {

	@Autowired
	StudentGroupRepository studentGroupRepository;
	
	public List<StudentGroup> findStudentGroupWithStudentsAndCourses() {
		List<StudentGroup> studentGroups = new ArrayList<>();
		studentGroupRepository.findAll().forEach(s -> studentGroups.add(s));
		
		//For eager loading of students and courses from the student group
		for (StudentGroup studentGroup : studentGroups) {
			List<Course> courses = studentGroup.getCourses();
			if (courses.size() > 0) {
				//Eager loading of grades in a course
				for (Course course : courses) {
					if (course.getGrades().size() > 0) {
						course.getGrades().get(0).getId();											
					}					
				}
			}
			if (studentGroup.getStudents().size() > 0) {
				studentGroup.getStudents().get(0).getId();
			}
		}
		
		return studentGroups;
	}
	
}
