package com.alexey.mvc;

import com.alexey.mvc.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UsersController {

    // Log all events in a configurable way
    static Logger log = Logger.getLogger(UsersController.class.getName());

    // Dependency injection of the DAO singleton that deals with users persistence
    @Resource(name = "usersJpaDao")
    IUserDAO userDao;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public User createUser(User user) {

        if (isValid(user)) {
            log.info("Creating new user");

            return userDao.create(user);
        } else {
            log.warning("Invalid user data received");
            return null;
        }
    }

    private boolean isValid(User user) {
        return !StringUtils.isEmpty(user.getName());
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<User> getAllUsers() {
        log.info("Returning all users");

        return userDao.selectAll();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
        log.info("Deleting user");

        if (id != null) {
            if (userDao.delete(Integer.parseInt(id))) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
