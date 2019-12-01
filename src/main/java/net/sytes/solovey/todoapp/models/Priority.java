package net.sytes.solovey.todoapp.models;

public enum Priority {
	HAD_TO_DO_YESTERDAY("Надо было сделать еще вчера"), URGENT("Срочно"), NOT_URGENT("Не срочно"), MAY_NOT_TO_DO("Можно и не делать");
	private String value;

	Priority(String value) {
		this.value = value;
	}

	String getValue() {
		return this.value;
	}

	public String toString() {
		return this.value;
	}
}
