package ibf2022.paf.assessment.server.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@Controller
public class TasksController {

    @Autowired
    TodoService todoService;

    @PostMapping(path = "/task", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView postTask(@RequestBody MultiValueMap<String, String> formData) {
        String username = formData.getFirst("username");
        int taskNum = (formData.size() - 1) / 3;
        System.out.println("task number >>>>>>>>>>>>>>>" + taskNum);

        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskNum; i++) {
            String description = "description-%d".formatted(i);
            String priority = "priority-%d".formatted(i);
            String dueDate = "dueDate-%d".formatted(i);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + description + priority +
                    dueDate);
            Task task = new Task(formData.getFirst(description),
                    Integer.parseInt(formData.getFirst(priority)),
                    LocalDate.parse(formData.getFirst(dueDate)));
            tasks.add(task);
        }

        todoService.upsertTask(tasks, username);
        ModelAndView mav = null;
        try {
            mav = new ModelAndView("result.html");
            mav.setStatus(HttpStatus.OK);
            mav.addObject("taskCount", taskNum);
            mav.addObject("username", username);
        } catch (Exception e) {
            mav = new ModelAndView("error.html");
            mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return mav;
    }
}
