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
	SelectStudentGroupView selectStudentGroupView;
	@Autowired
	CatalogView catalogView;
	@Autowired
	StudentGroupService studentGroupService;
	
	@Override
	protected void init(VaadinRequest request) {

		VaadinSession session = UI.getCurrent().getSession();
		//if there is no authenticated user go to log in form
		if (session.getAttribute(USER) == null) {
			//redirect to login form
			LoginForm loginForm = new LoginForm();
			loginForm.addLoginListener(loginEvent -> {
				String userName = loginEvent.getLoginParameter(USERNAME_FIELD);
				String password = loginEvent.getLoginParameter(PASSWORD_FIELD);
				User userFromDb = studentGroupService.findByUserName(userName);
				boolean successfulLogin = false;
				
				if (userFromDb != null) {
					String passwordFromDb = userFromDb.getPassword();
					if (BCrypt.checkpw(password, passwordFromDb)) {
						successfulLogin = true;
						//TODO rediret to start page;
						Notification.show("Success");
					} 
				}
				
				if (! successfulLogin) {
					Notification.show("Username or password incorrect", Type.ERROR_MESSAGE);
				}
				
			});
			setContent(loginForm);
		} else {
			VerticalLayout mainPanel = new VerticalLayout();
			
			Label staticArea = new Label("This is a static area");
			CssLayout dummyLayout = new CssLayout();
			dummyLayout.addStyleName("my-class");
			dummyLayout.setId("my-id");
			
			mainPanel.addComponent(staticArea);
			mainPanel.addComponent(dummyLayout);

			Navigator navigator = new Navigator(UI.getCurrent(), dummyLayout);
			
			//Register views for navigation
			navigator.addView("", selectStudentGroupView);
			navigator.addView("catalogView", catalogView);
			
			setContent(mainPanel);			
		}
		
		
	}

}
