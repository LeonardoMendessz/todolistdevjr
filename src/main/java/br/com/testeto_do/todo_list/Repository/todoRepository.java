package br.com.testeto_do.todo_list.Repository;

import br.com.testeto_do.todo_list.Entity.todoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface todoRepository extends JpaRepository<todoEntity, Long> {

}
