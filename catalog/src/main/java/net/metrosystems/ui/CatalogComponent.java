package net.metrosystems.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import net.metrosystems.domain.Course;
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
					genericComponenet = new Label(i + "" +  j);
				}
				
				addComponent(genericComponenet, i, j);
			}
		}
	}
}
