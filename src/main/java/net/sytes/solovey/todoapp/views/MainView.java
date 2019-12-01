package net.sytes.solovey.todoapp.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import net.sytes.solovey.todoapp.models.Task;
import net.sytes.solovey.todoapp.repos.TaskRepo;
import net.sytes.solovey.todoapp.views.component.HelpDialog;
import net.sytes.solovey.todoapp.views.component.TaskEditor;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {
	private final TaskRepo taskRepo;
	private final TaskEditor taskEditor;
	private final HelpDialog helpDialog = new HelpDialog();

	private Grid<Task> taskGrid = new Grid<>(Task.class);
	private final TextField filter = new TextField();
	private final Button addNewTaskButton = new Button("Добавить задачу", VaadinIcon.PLUS.create());
	private final Button helpButton = new Button("Справка", VaadinIcon.QUESTION.create());
	private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewTaskButton, helpButton);


	@Autowired
	public MainView(TaskRepo taskRepo, TaskEditor taskEditor) {
		this.taskRepo = taskRepo;
		this.taskEditor = taskEditor;
		toolbar.setAlignItems(Alignment.CENTER);
		setAlignItems(Alignment.CENTER);
		setSizeFull();

		taskGrid.setColumns("name", "details", "priority");
		taskGrid.getColumnByKey("name")
				.setFlexGrow(2)
				.setHeader("Заголовок");
		taskGrid.getColumnByKey("details")
				.setFlexGrow(6)
				.setHeader("Описание");
		taskGrid.getColumnByKey("priority")
				.setFlexGrow(1)
				.setHeader("Приоритет");
		taskGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		taskGrid.setSelectionMode(Grid.SelectionMode.NONE);
		taskGrid.addItemDoubleClickListener(e -> taskEditor.editTask(e.getItem()));

		filter.setPlaceholder("Поиск");
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(field -> fillTaskGrid(field.getValue()));

		addNewTaskButton.addClickListener(e -> taskEditor.editTask(new Task()));

		taskEditor.setChangeHandler(() -> {
			taskEditor.close();
			fillTaskGrid(filter.getValue());
		});

		helpButton.addClickListener(e -> this.helpDialog.open());
		add(toolbar, taskGrid, taskEditor);
		fillTaskGrid("");
	}

	private void fillTaskGrid(String searchString) {
		if (searchString.isEmpty()) {
			taskGrid.setItems(taskRepo.findAll());
		} else {
			taskGrid.setItems(taskRepo.searchTasks(searchString));
		}
	}
}
