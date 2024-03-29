package org.baldi.todoapp.persistence.repository;

import org.baldi.todoapp.persistence.entity.Task;
import org.baldi.todoapp.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findAllByTaskStatus(TaskStatus status);

    @Modifying
    @Query(value = "update Task set finished=true where id=:id", nativeQuery = true)
    public void markTaskAsFinished(@Param("id") Long id);
}
