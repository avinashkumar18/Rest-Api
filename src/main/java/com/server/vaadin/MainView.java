package com.server.vaadin;

import org.springframework.util.StringUtils;


import com.server.dto.ServerDto;
import com.server.repo.ServerRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout{
	
	private final ServerRepo repo;
	
	private final ServerEditor editor;
	
	final Grid<ServerDto> grid;
	
	final TextField filter;

	private final Button addNewBtn;

	public MainView(ServerRepo repo, ServerEditor editor) {
		this.repo = repo;
		this.editor = editor;
		this.grid = new Grid<>(ServerDto.class);
		this.filter = new TextField();
		this.addNewBtn = new Button("New Server", VaadinIcon.PLUS.create());
		
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		add(actions, grid, editor);
		
		grid.setHeight("300px");
		grid.setColumns("id", "name", "language", "framework");
		grid.getColumnByKey("id").setWidth("300px").setFlexGrow(0);
		
		filter.setPlaceholder("Filter by name");
		
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listServers(e.getValue()));
		
		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editServer(e.getValue());
		});
		
		addNewBtn.addClickListener(e -> editor.editServer(new ServerDto("","","")));
		
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listServers(filter.getValue());
		});
		
		listServers(null);
	}

	void  listServers(String value) {
		if (StringUtils.isEmpty(value)) {
			grid.setItems(repo.findAll());
		}
		else {
			grid.setItems(repo.findByName(value));
		}
	}

}
