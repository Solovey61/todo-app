package net.sytes.solovey.todoapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;
	private String details;

	@Enumerated(EnumType.STRING)
	private Priority priority;

	@CreationTimestamp
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	@Column(updatable = false)
	private LocalDateTime creationTime;

	@UpdateTimestamp
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private LocalDateTime lastUpdateTime;
}

