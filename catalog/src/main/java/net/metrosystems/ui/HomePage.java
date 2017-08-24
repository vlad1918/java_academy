package net.metrosystems.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import net.metrosystems.domain.StudentGroup;
import net.metrosystems.repository.StudentGroupRepository;

@SpringUI
public class HomePage extends UI {
	
	@Autowired
	SelectStudentGroupView selectStudentGroupView;
	@Autowired
	CatalogView catalogView;
	
	@Override
	protected void init(VaadinRequest request) {

		VerticalLayout mainPanel = new VerticalLayout();

		Label staticArea = new Label("This is a static area");
		CssLayout dummyLayout = new CssLayout();
		
		mainPanel.addComponent(staticArea);
		mainPanel.addComponent(dummyLayout);
		
		Navigator navigator = new Navigator(UI.getCurrent(), dummyLayout);
		
		//Register views for navigation
		navigator.addView("", selectStudentGroupView);
		navigator.addView("catalogView", catalogView);
		
		setContent(mainPanel);
		
	}

}
