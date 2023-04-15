package ibf2022.paf.assessment.server.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7

@Service
public class TodoService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Transactional
    public String upsertTask(List<Task> tasks, String username) {
        Optional<User> u = userRepository.findUserByUsername(username);
        String userId;
        // if new user
        if (u.isEmpty()) {
            User newUser = new User();
            newUser.setUsername(username);
            userId = userRepository.insertUser(newUser);
            newUser.setUserId(userId);
        } else {
            // if existing user
            User existingUser = u.get();
            userId = existingUser.getUserId();
        }

        // insert task
        for (Task task : tasks) {
            task.setUserId(userId);
            taskRepository.addTask(task);
        }

        return userId;
    }

    public User findUserByUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    public LocalDate getLocalDate(String date) {
        return LocalDate.parse(date);
    }
}
