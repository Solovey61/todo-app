package net.sytes.solovey.todoapp.repos;

import net.sytes.solovey.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
	@Query("from Task t where concat(t.name, ' ', t.details) like concat('%', :search, '%') ")
	List<Task> searchTasks(@Param("search") String searchString);

	List<Task> findAll();
}
