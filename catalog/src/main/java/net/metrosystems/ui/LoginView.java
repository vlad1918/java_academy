package net.metrosystems.ui;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;

import net.metrosystems.controller.Role;
import net.metrosystems.controller.StudentGroupService;
import net.metrosystems.domain.User;

@UIScope
@SpringView
public class LoginView extends VerticalLayout implements View, UiConstants {

	@Autowired
	SelectStudentGroupView selectStudentGroupView;
	@Autowired
	CatalogView catalogView;
	
	@Autowired
	StudentGroupService studentGroupService;
	
	@Override
	public void enter(ViewChangeEvent event) {
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
					Notification.show("Success");
					UI.getCurrent().getSession().setAttribute(USER, userFromDb);
					Role role = userFromDb.getRole();
					if (role == Role.PROFESOR) {
						UI.getCurrent().getNavigator().addView(SELECT_STUDENT_GROUP_VIEW, selectStudentGroupView);
						UI.getCurrent().getNavigator().addView(CATALOG_VIEW, catalogView);
						UI.getCurrent().getNavigator().navigateTo(SELECT_STUDENT_GROUP_VIEW);
					} else if (role == Role.STUDENT) {
						UI.getCurrent().getNavigator().addView(CATALOG_VIEW, catalogView);
						UI.getCurrent().getNavigator().navigateTo(CATALOG_VIEW);
					} else {
						throw new IllegalStateException("Unkown role " + userFromDb.getRole());
					}
				} 
			}
			
			if (! successfulLogin) {
				Notification.show("Username or password incorrect", Type.ERROR_MESSAGE);
			}
		});
		
		removeAllComponents();
		addComponent(loginForm);
	}
}
