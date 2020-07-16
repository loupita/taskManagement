package org.sid.jwtspringsec.dao;

import org.sid.jwtspringsec.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
