package com.salesconnect.backend.transformer;

import com.salesconnect.backend.dto.ActivityDTO;
import com.salesconnect.backend.dto.CompanyDTO;
import com.salesconnect.backend.dto.TaskDTO;
import com.salesconnect.backend.dto.UserDTO;
import com.salesconnect.backend.entity.Activity;
import com.salesconnect.backend.entity.Company;
import com.salesconnect.backend.entity.Task;
import com.salesconnect.backend.entity.User;

public class TaskTransformer extends Transformer<Task, TaskDTO> {
    @Override
    public Task toEntity(TaskDTO taskDTO) {
        if (taskDTO == null)
            return null;
        else {
            Transformer<User, UserDTO> userTransformer = new UserTransformer();
            Task task = new Task();
            task.setTaskId(taskDTO.getTaskId());
            task.setType(taskDTO.getType());
            task.setSubject(taskDTO.getSubject());
            task.setDescription(taskDTO.getDescription());
            task.setStatus(taskDTO.getStatus());
            task.setDueDate(taskDTO.getDueDate());
            task.setUser(userTransformer.toEntity(taskDTO.getUserDTO()));
            return task;
        }
    }

    @Override
    public TaskDTO toDTO(Task task) {
        if (task == null)
            return null;
        else {
            Transformer<User, UserDTO> userTransformer = new UserTransformer();
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTaskId(task.getTaskId());
            taskDTO.setType(task.getType());
            taskDTO.setSubject(task.getSubject());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setStatus(task.getStatus());
            taskDTO.setDueDate(task.getDueDate());
            taskDTO.setUserDTO(userTransformer.toDTO(task.getUser()));
            return taskDTO;
        }
    }
}
