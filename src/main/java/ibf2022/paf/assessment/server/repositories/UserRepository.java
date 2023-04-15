package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.User;
import static ibf2022.paf.assessment.server.repositories.DBQueries.*;

// TODO: Task 3

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Optional<User> findUserByUsername(String username) {
        SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_USER_BY_USERNAME, username);
        while (rs.next()) {
            return Optional.of(User.createFromResult(rs));
        }
        return Optional.empty();
    }

    public String insertUser(User user) {
        String userId = UUID.randomUUID().toString().substring(0, 8);
        int row = jdbcTemplate.update(INSERT_USER, userId, user.getUsername(), user.getName());
        if (row >= 1) {
            return userId;
        }
        return "Error insert";
    }
}
