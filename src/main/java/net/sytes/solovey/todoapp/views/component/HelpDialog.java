package net.sytes.solovey.todoapp.views.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class HelpDialog extends Dialog {
	private final VerticalLayout layout = new VerticalLayout();
	private final Label label = new Label();
	private final Button buttonOk = new Button("OK");

	public HelpDialog() {
		label.setText("Для добавления новой задачи необходимо нажать на соответствующую кнопку сверху таблицы. " +
				"Для редактирования существующей задачи необходимо выполнить двойной клик на соответстующей задаче. " +
				"В меню редактирования можно дополнительно увидеть дату и время создания задачи, а также дату и время последнего изменения. " +
				"Удаление производится через меню редактирования, нажатием на соответствующую кнопку. " +
				"Для быстрого поиска по списку задач необходимо ввести искомый текст в соответствующее поле сверху таблицы.");
		label.setMaxWidth("400px");
		buttonOk.addClickListener(e -> close());
		layout.setAlignItems(FlexComponent.Alignment.CENTER);
		layout.add(label, buttonOk);
		add(layout);
	}
}
