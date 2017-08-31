package net.metrosystems.ui;

import java.util.Date;
import java.util.List;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import net.metrosystems.controller.StudentGroupService;
import net.metrosystems.domain.Course;
import net.metrosystems.domain.Grade;
import net.metrosystems.domain.Student;

public class CatalogAddGradeListener implements ClickListener {

	private StudentGroupService service;
	private Student student;
	private Course course;
	private Grid<Grade> grid;
	private List<Grade> currentGrades;
	
	//Override default constructor
	CatalogAddGradeListener(StudentGroupService service, 
			Student student, Course course, Grid<Grade> grid, List<Grade> currentGrades) {
		this.service = service;
		this.student = student;
		this.course = course;
		this.grid = grid;
		this.currentGrades = currentGrades;
	}
	
	@Override
	public void buttonClick(ClickEvent event) {

		FormLayout addGradeForm = new FormLayout();
		TextField gradeField = new TextField("Grade");
		DateField dateField = new DateField("Date");
		Button okButton = new Button("Ok");
		
		addGradeForm.addComponent(gradeField);
		addGradeForm.addComponent(dateField);
		addGradeForm.addComponent(okButton);
		
		Window window = new Window("Add grade");
		window.setModal(true);
        window.setWidth(300.0f, Unit.PIXELS);
        window.setContent(addGradeForm);
        
        UI.getCurrent().addWindow(window);
        
        okButton.addClickListener(e -> {
        	try {
	        	Date date = java.sql.Date.valueOf(dateField.getValue());
	        	int grade = Integer.valueOf(gradeField.getValue());
	        	Grade gradeEntity = service.saveGrade(course, student, grade, date);
	        	//Also update UI
	        	currentGrades.add(gradeEntity);
	        	grid.setItems(currentGrades);

        	} catch (NumberFormatException nef) {
        		Notification.show("Please enter a valid number for the grade"
        				, Type.ERROR_MESSAGE);
        	}
        	
        	window.close();
        });
		
	}


}
