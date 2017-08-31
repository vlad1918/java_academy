package net.metrosystems.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.renderers.DateRenderer;

import net.metrosystems.controller.StudentGroupService;
import net.metrosystems.domain.Course;
import net.metrosystems.domain.Grade;
import net.metrosystems.domain.Student;
import net.metrosystems.domain.StudentGroup;

public class CatalogComponent extends GridLayout {

	private DateFormat roDateFormat = new SimpleDateFormat("dd.MM.YYYY");
	
	public CatalogComponent(StudentGroup studentGroup, StudentGroupService service) {

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
					genericComponenet = new Label(course.getName() 
							+ "(" + course.getProfesor().getName() + ")" );
				} else {					
					List<Grade> grades = new ArrayList<Grade>();
					Student student = studentGroup.getStudents().get(j-1);
					Course course = studentGroup.getCourses().get(i-1);
					
					//versiunea1
//					List<Grade> gradesFromCourses = course.getGrades();
//					for (Grade grade : gradesFromCourses) {
//						if (grade.getStudent().equals(student)) {
//							grades.add(grade);
//						}
//					}
					
//					course.getGrades().forEach(grade -> {
//						if (grade.getStudent().equals(student)) {
//							grades.add(grade);
//						}
//					});
					
					//versiunea2
					grades = course.getGrades().stream()
						.filter(grade -> grade.getStudent().equals(student))
						.collect(Collectors.toList());
					
					Grid<Grade> gradeGrid = new Grid<Grade>();
					gradeGrid.setItems(grades);		
					
					gradeGrid.addColumn(Grade::getValue).setCaption("Nota");
					gradeGrid.addColumn(Grade::getDate)
						.setRenderer(new DateRenderer(roDateFormat))
						.setCaption("Data");
					
					gradeGrid.setWidth(190, Unit.PIXELS);
					gradeGrid.setHeightMode(HeightMode.ROW);
					gradeGrid.setHeightByRows(5);
					
					CssLayout gradeLayout = new CssLayout();

					Button addGradeButton = new Button("Add Grade");
					addGradeButton.addClickListener(
							new CatalogAddGradeListener(service, student, course, gradeGrid, grades));
							
					gradeLayout.addComponent(gradeGrid);
					gradeLayout.addComponent(addGradeButton);
					
					genericComponenet = gradeLayout;
					
				}
				
				addComponent(genericComponenet, i, j);
			}
		}
	}
}
