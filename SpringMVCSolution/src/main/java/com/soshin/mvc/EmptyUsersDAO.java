package com.soshin.mvc;

import com.soshin.mvc.model.User;
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
    private final RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(final ResultSet resultSet, final int i) throws SQLException {
            final User u = new User();

            u.setId(resultSet.getInt("ID"));
            u.setName(resultSet.getString("NAME"));

            return u;
        }
    };

    @Override
    public User create(final User user) {
        final User u = new User();
        return u;
    }

    @Override
    public boolean delete(final Integer id) {
        final Map<String, Object> params = new HashMap<>();

        params.put("id", id);
        final int rows = this.template.update("DELETE FROM USERS WHERE ID = :id", params);

        return rows > 0;
    }

    @Override
    public User select(final Integer id) {
        return null;
    }

    @Override
    public User update(final User user) {
        return null;
    }

    @Override
    public Collection<User> selectAll() {
        return this.template.query("SELECT * FROM USERS",
                this.userRowMapper);
    }
}
