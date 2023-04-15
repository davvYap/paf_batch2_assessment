package ibf2022.paf.assessment.server.repositories;

public class DBQueries {

    public static final String GET_USER_BY_USERNAME = "select * from user where username = ?";
    public static final String INSERT_USER = """
            insert into user(user_id,username,name)
            values
            (?,?,?);
                """;
    public static final String INSERT_TASK = """
            insert into task(description, priority, due_date, user_id)
            values
            (?, ?, ?, ?);
                """;
}
