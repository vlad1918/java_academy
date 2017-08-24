package net.metrosystems.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

import net.metrosystems.domain.StudentGroup;
import net.metrosystems.repository.StudentGroupRepository;

@UIScope
@SpringView
public class SelectStudentGroupView extends VerticalLayout implements View {

	@Autowired
	StudentGroupRepository studentGroupRepository;
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		//TODO get studentGroups for a specific professor, not all of them
		List<StudentGroup> allStudentGroups = new ArrayList<>();
		studentGroupRepository.findAll().forEach(s -> allStudentGroups.add(s));
		
		ComboBox<StudentGroup> studentGroupsCombo = new ComboBox<>("Select a class of students:");
		studentGroupsCombo.setItems(allStudentGroups);
		
		//if there is a studentGroup in session then preselect it in the combo box
		StudentGroup sessionStudentGroup = (StudentGroup) UI.getCurrent().getSession().getAttribute("selectedStudentGroup");
		if (sessionStudentGroup != null) {
			studentGroupsCombo.setValue(sessionStudentGroup);
		}
		
		Button actionButton = new Button("See catalog");
		
		actionButton.addClickListener(e -> {
			StudentGroup selectedStudentGroup = studentGroupsCombo.getValue();
			
			if (selectedStudentGroup == null) {
				Notification.show("Please select a class of students to see the catalog"
						, Type.ERROR_MESSAGE);
			} else {
				//Go to catalogView
				UI.getCurrent().getSession().setAttribute("selectedStudentGroup", studentGroupsCombo.getValue());
				UI.getCurrent().getNavigator().navigateTo("catalogView");
			}
			
		});
		
		//remove all componenets and add them
		removeAllComponents();
		addComponent(studentGroupsCombo);
		addComponent(actionButton);
		
	}
}
