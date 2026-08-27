package com.example.taskmanager.controller;

import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.model.Task;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final ConcurrentHashMap<Long, Task> taskMap = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // GET /tasks - return all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }

    // POST /tasks - create a new task
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        long id = idGenerator.getAndIncrement();
        task.setId(id);
        taskMap.put(id, task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    // GET /tasks/{id} - optional, but useful
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        Task task = taskMap.get(id);
        if (task == null) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        return task;
    }

    // PUT /tasks/{id} - update existing task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task updatedTask) {
        Task existingTask = taskMap.get(id);
        if (existingTask == null) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCompleted(updatedTask.getCompleted());
        return new ResponseEntity<>(existingTask, HttpStatus.OK);
    }

    // DELETE /tasks/{id} - delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Task task = taskMap.remove(id);
        if (task == null) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}