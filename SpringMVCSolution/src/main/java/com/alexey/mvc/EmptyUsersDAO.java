package com.alexey.mvc;

import com.alexey.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Implement CRUD for users
 */
@Repository
public class EmptyUsersDAO implements IUserDAO {

    @Autowired
    NamedParameterJdbcTemplate template;

    @Autowired
    SimpleJdbcInsert insert;

    /**
     * This object converts DB results to Java objects
     */
    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User u = new User();

            u.setId(resultSet.getInt("ID"));
            u.setName(resultSet.getString("NAME"));

            return u;
        }
    };

    @Override
    public User create(User user) {
        User u = new User();
        return u;
    }

    @Override
    public boolean delete(Integer id) {
        Map<String, Object> params = new HashMap<>();

        params.put("id", id);
        int rows = template.update("DELETE FROM USERS WHERE ID = :id", params);

        return rows > 0;
    }

    @Override
    public User select(Integer id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Collection<User> selectAll() {
        return template.query("SELECT * FROM USERS",
                userRowMapper);
    }
}
