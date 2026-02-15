package com.elie.question5_task_api.controller.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elie.question5_task_api.model.task.Task;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {


    private List<Task> tasks = new ArrayList<>();

    // Initialize sample tasks
    public TaskController() {
        tasks.add(new Task(1L, "Finish Assignment", "Complete Spring Boot assignment", false, "HIGH", "2026-02-20"));
        tasks.add(new Task(2L, "Grocery Shopping", "Buy vegetables and fruits", true, "MEDIUM", "2026-02-15"));
        tasks.add(new Task(3L, "Workout", "1 hour cardio session", false, "LOW", "2026-02-16"));
        tasks.add(new Task(4L, "Call Client", "Discuss project updates", false, "HIGH", "2026-02-18"));
        tasks.add(new Task(5L, "Read Book", "Read chapters 4 and 5", true, "LOW", "2026-02-14"));
    }

    // GET all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(tasks);
    }

    // GET task by ID
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // GET tasks by completion status
    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam boolean completed) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted() == completed) {
                result.add(task);
            }
        }
        return ResponseEntity.ok(result);
    }

    // GET tasks by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                result.add(task);
            }
        }
        return ResponseEntity.ok(result);
    }

    // POST create new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        tasks.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    // PUT update task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setCompleted(updatedTask.isCompleted());
                task.setPriority(updatedTask.getPriority());
                task.setDueDate(updatedTask.getDueDate());
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // PATCH mark task as completed
    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskCompleted(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setCompleted(true);
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                tasks.remove(task);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
 
}
