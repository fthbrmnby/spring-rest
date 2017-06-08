package spring;

import db.DB;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import users.User;

@RestController
public class DemoController {

    DB mongodb = new DB();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot";
    }

    @RequestMapping(value = "/users/{user_id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("user_id") ObjectId userID) {
        return mongodb.getUser(userID);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers() {
        return mongodb.getUsers();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void createUser(@RequestBody User u) {
        try {
            mongodb.addUser(u);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public void deleteUsers() {
        try {
            mongodb.removeUsers();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @RequestMapping(value = "/users/{user-id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("user-id") ObjectId userID) {
        try {
            mongodb.removeUser(userID);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @RequestMapping(value = "/users/{user-id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable("user-id") ObjectId userID, @RequestBody User u) {
        return mongodb.updateUser(userID, u);
    }
}
