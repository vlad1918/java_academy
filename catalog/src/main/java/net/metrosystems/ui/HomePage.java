package net.metrosystems.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import net.metrosystems.domain.StudentGroup;
import net.metrosystems.repository.StudentGroupRepository;

@SpringUI
public class HomePage extends UI {

	@Autowired
	StudentGroupRepository studentGroupRepository;
	
	@Override
	protected void init(VaadinRequest request) {

		//TODO get studentGroups for a specific professor, not all of them
		List<StudentGroup> allStudentGroups = new ArrayList<>();
		studentGroupRepository.findAll().forEach(s -> allStudentGroups.add(s));
		
		ComboBox<StudentGroup> studentGroupsCombo = new ComboBox<>("Select a class of students:");
		studentGroupsCombo.setItems(allStudentGroups);
		Button actionButton = new Button("See catalog");
		
		actionButton.addClickListener(e -> {
			StudentGroup selectedStudentGroup = studentGroupsCombo.getValue();
			
			if (selectedStudentGroup == null) {
				Notification.show("Please select a class of students to see the catalog"
						, Type.ERROR_MESSAGE);
			} else {
				Notification.show("In the next workshop "
						+ "you will see the catalog of class " + selectedStudentGroup.getName(),
						Type.TRAY_NOTIFICATION);
			}
			
		});
		
		
		VerticalLayout mainPanel = new VerticalLayout();
		mainPanel.addComponent(studentGroupsCombo);
		mainPanel.addComponent(actionButton);
		
		setContent(mainPanel);
		
	}

}
