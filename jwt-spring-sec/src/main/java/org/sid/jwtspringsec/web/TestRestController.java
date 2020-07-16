package org.sid.jwtspringsec.web;

import org.sid.jwtspringsec.dao.TaskRepository;
import org.sid.jwtspringsec.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestRestController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> listTasks(){
        return taskRepository.findAll();
    }
    @PostMapping("/tasks")
    public Task save (@RequestBody Task t){
        return taskRepository.save(t);
    }
}
