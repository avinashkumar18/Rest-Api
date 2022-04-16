package com.server.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.server.controller.ServerController;
import com.server.dto.ServerDto;
import com.server.repo.ServerRepo;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class ServerEditor extends VerticalLayout implements KeyNotifier{
	
	private final ServerRepo repo;
	
	private ServerDto dto;
	
	@Autowired
	private ServerController controller;
	
	TextField name = new TextField("name");
	TextField language = new TextField("Language");
	TextField framework = new TextField("Framework");
	
	Button save = new Button("Save", VaadinIcon.CHECK.create());
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", VaadinIcon.TRASH.create());
	HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
	
	Binder<ServerDto> binder = new Binder<>(ServerDto.class);
	private ChangeHandler changeHandler;
	
	@Autowired
	public ServerEditor(ServerRepo repo) {
		this.repo = repo;
		
		add(name, language, framework, actions);
		
		binder.bindInstanceFields(this);
		setSpacing(true);
		
		save.getElement().getThemeList().add("primary");
		delete.getElement().getThemeList().add("error");
		
		addKeyPressListener(Key.ENTER, e -> save());
		
		save.addClickListener(e -> save());
		delete.addClickListener(e -> delete());
		cancel.addClickListener(e -> editServer(dto));
		setVisible(false);
		
	}
	
	void delete() {
		controller.DeleteServerById(dto.getId());
		changeHandler.onChange();
	}
	
	void save() {
		controller.saveServer(dto);
		changeHandler.onChange();
	}
	
	public interface ChangeHandler {
		void onChange();
	}
	
	public final void editServer(ServerDto s) {

		if (s == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = s.getId() != null;
		if (persisted) {

			dto = repo.findById(s.getId()).get();
		}
		else {
			dto = s;
		}
		cancel.setVisible(persisted);

		binder.setBean(dto);

		setVisible(true);

		name.focus();

	}
	
	public void setChangeHandler(ChangeHandler h) {
		changeHandler = h;
	}
	
	

}
