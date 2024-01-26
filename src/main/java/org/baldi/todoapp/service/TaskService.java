package org.baldi.todoapp.service;

import jakarta.transaction.Transactional;
import org.baldi.todoapp.exceptions.Exceptions;
import org.baldi.todoapp.mapper.TaskInDTOToTask;
import org.baldi.todoapp.persistence.entity.Task;
import org.baldi.todoapp.persistence.entity.TaskStatus;
import org.baldi.todoapp.persistence.repository.TaskRepository;
import org.baldi.todoapp.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO) {
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }

    public List<Task> findAll() {
        return this.repository.findAll();
    }

    public List<Task> findAllByStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskFinished(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new Exceptions("Task no found", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskAsFinished(id);
    }

    public void deleteById(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new Exceptions("Task no found", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }

}
