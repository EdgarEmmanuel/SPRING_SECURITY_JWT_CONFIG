package com.jwt_spring.web;

import com.jwt_spring.dao.ITask;
import com.jwt_spring.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITask itask;

    @GetMapping(value={""})
    public List<Task> getAllTasks(){
        return itask.findAll();
    }

    @PostMapping(value={""},consumes = {"application/json"})
    public Task saveTask(@RequestBody Task t){
        return itask.save(t);
    }


}
