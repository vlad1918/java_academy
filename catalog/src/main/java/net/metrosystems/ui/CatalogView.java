package net.metrosystems.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringView
public class CatalogView extends VerticalLayout implements View {

	@Override
	public void enter(ViewChangeEvent event) {

		Button button = new Button("<< Go Back");
		button.addClickListener(e -> UI.getCurrent().getNavigator().navigateTo(""));
		
		removeAllComponents();
		addComponent(button);
		
	}
}
