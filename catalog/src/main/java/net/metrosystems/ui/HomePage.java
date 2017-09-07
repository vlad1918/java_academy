package net.metrosystems.ui;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import net.metrosystems.controller.StudentGroupService;
import net.metrosystems.domain.StudentGroup;
import net.metrosystems.domain.User;
import net.metrosystems.repository.StudentGroupRepository;

@SpringUI
@Theme("myTheme")
public class HomePage extends UI implements UiConstants {

	@Autowired
	LoginView loginView;
	
	@Autowired
	StudentGroupService studentGroupService;
	
	@Override
	protected void init(VaadinRequest request) {

		VaadinSession session = UI.getCurrent().getSession();
		
		VerticalLayout mainPanel = new VerticalLayout();
		
		Label staticArea = new Label("This is a static area");
		CssLayout dummyLayout = new CssLayout();
		dummyLayout.addStyleName("my-class");
		dummyLayout.setId("my-id");
		
		mainPanel.addComponent(staticArea);
		mainPanel.addComponent(dummyLayout);

		Navigator navigator = new Navigator(UI.getCurrent(), dummyLayout);
		
		if (session.getAttribute(USER) == null) {
			navigator.addView("", loginView);
		}
		
		setContent(mainPanel);					
	}
}
