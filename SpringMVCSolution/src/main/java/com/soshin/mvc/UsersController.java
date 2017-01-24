package com.soshin.mvc;

import com.soshin.mvc.model.User;
import com.soshin.mvc.model.UserDocument;
import com.soshin.mvc.repo.IUserDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UsersController {

    // Log all events in a configurable way
    private static final Logger log = Logger.getLogger(UsersController.class.getName());

    // Dependency injection of the DAO singleton that deals with users persistence
    @Resource(name = "usersJpaDao")
    IUserDAO userDao;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public User createUser(final User user) {

        if (isValid(user)) {
            log.info("Creating new user");

            return this.userDao.create(user);
        } else {
            log.warning("Invalid user data received");
            return null;
        }
    }

    private boolean isValid(final User user) {
        return !StringUtils.isEmpty(user.getName());
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<User> getAllUsers() {
        log.info("Returning all users");

        return this.userDao.selectAll();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") final String id) {
        log.info("Deleting user");

        if (id != null) {
            if (this.userDao.delete(Integer.parseInt(id))) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/documents")
    @ResponseBody
    public Collection<UserDocument> getDocumentsForUser(@PathVariable("id") final Integer userId) {
        log.info("Getting user documents");

        if (userId != null) {
            return this.userDao.select(userId).getDocuments();
        } else {
            return Collections.emptyList();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/documents")
    public ResponseEntity<HttpStatus> addDocument(@RequestParam("name") final String documentName,
                                                  @PathVariable("id") final Integer userId) {

        final User user = this.userDao.select(userId);
        if (user != null) {
            final List<UserDocument> documents = user.getDocuments();

            // In two steps for clarity
            final UserDocument document = new UserDocument(documentName);
            document.setUser(user);
            documents.add(document);

            this.userDao.update(user);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
