package com.jwt_spring.dao;
import com.jwt_spring.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITask extends JpaRepository<Task,Integer> {
    public Task findByTaskname(String taskname);
}
