package ibf2022.paf.assessment.server.models;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

// TODO: Task 4

public class Task {

    private int taskId;
    private String description;
    private int priority;
    private LocalDate dueDate;
    private String userId;

    public Task() {
    }

    public Task(String description, int priority, LocalDate dueDate) {
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", description=" + description + ", priority=" + priority + ", dueDate="
                + dueDate + "]";
    }

    public Date toSqlDate() {
        LocalDateTime localDateTime = this.getDueDate().atStartOfDay();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        Date date = new java.sql.Date(instant.toEpochMilli());
        return date;
    }

}
