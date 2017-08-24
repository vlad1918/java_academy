package net.metrosystems.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import net.metrosystems.domain.StudentGroup;

@UIScope
@SpringView
public class CatalogView extends VerticalLayout implements View {

	@Override
	public void enter(ViewChangeEvent event) {

		StudentGroup selectedStudentGroup = (StudentGroup) UI.getCurrent().getSession()
				.getAttribute("selectedStudentGroup");
		Label title = new Label("<h1 style=\"color: red;\">You are seeing the catalog of class " 
				+ selectedStudentGroup.getName() + "</h1>",
				ContentMode.HTML);

		Button button = new Button("<< Go Back");
		button.addClickListener(e -> UI.getCurrent().getNavigator().navigateTo(""));
		button.addStyleName(ValoTheme.BUTTON_DANGER);
		
		removeAllComponents();
		addComponent(title);
		addComponent(button);

	}
}
