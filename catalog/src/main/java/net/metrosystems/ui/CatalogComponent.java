package net.metrosystems.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import net.metrosystems.domain.Course;
import net.metrosystems.domain.Grade;
import net.metrosystems.domain.Student;
import net.metrosystems.domain.StudentGroup;

public class CatalogComponent extends GridLayout {

	public CatalogComponent(StudentGroup studentGroup) {

		int nbCourses = studentGroup.getCourses().size();
		int nbStudents = studentGroup.getStudents().size();

		this.setId("catalog-component");
		this.setColumns(nbCourses + 1);
		this.setRows(nbStudents + 1);

		for (int i = 0; i <= nbCourses; i++) {
			for (int j = 0; j <= nbStudents; j++) {
				Component genericComponenet = null;
				if (i == 0 && j == 0) {
					genericComponenet = new Label("X");
				} else if (i == 0) {
					Student student = studentGroup.getStudents().get(j-1);
					genericComponenet = new Label(student.getLastName() + " " + student.getFirstName());
				}  else if (j == 0) {
					Course course = studentGroup.getCourses().get(i-1);
					genericComponenet = new Label(course.getName());
				} else {					
					List<Grade> grades = new ArrayList<Grade>();
					Student student = studentGroup.getStudents().get(j-1);
					Course course = studentGroup.getCourses().get(i-1);
					
					System.out.println(student.getLastName() + "-" + course.getName());
					
					//versiunea1
					List<Grade> gradesFromCourses = course.getGrades();
					for (Grade grade : gradesFromCourses) {
						if (grade.getStudent().equals(student)) {
							grades.add(grade);
						}
					}
					
//					.forEach(grade -> {
//						if (grade.getStudent().equals(student)) {
//							grades.add(grade);
//						}
//					});
					
					//versiunea2
//					grades = course.getGrades().stream()
//						.filter(grade -> grade.getStudent().equals(student))
//						.collect(Collectors.toList());
					
					Grid<Grade> gradeGrid = new Grid<Grade>(Grade.class);
					gradeGrid.setItems(grades);		
					
					genericComponenet = gradeGrid;
					
				}
				
				addComponent(genericComponenet, i, j);
			}
		}
	}
}
