package ibf2022.paf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;

import static ibf2022.paf.assessment.server.repositories.DBQueries.*;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;

// TODO: Task 6

@Repository
public class TaskRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTask(Task task) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_TASK, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getDescription());
            statement.setInt(2, task.getPriority());
            statement.setDate(3, task.toSqlDate());
            statement.setString(4, task.getUserId());
            return statement;
        }, keyHolder);

        BigInteger primaryKey = (BigInteger) keyHolder.getKey();
        return primaryKey.intValue();
    }
}
