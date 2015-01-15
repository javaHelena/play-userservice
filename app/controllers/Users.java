package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.users.listUsers;

import java.util.List;

/**
 * @author : Helena Hjertén
 */
public class Users extends Controller {


    public static Result listAll() {
        List<User> users = User.find.all();
        if (users==null) {
            return ok(index.render("No users in database"));
        }
        return ok(listUsers.render(users));
    }


    // API ACTIONS:

    public static Result authenticateUser(){
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        } else {
            String username = json.findPath("username").asText();
            String pwd = json.findPath("password").asText();
            if(username == null) {
                return badRequest("Missing parameter [username]");
            } else {
                User user = User.findUserByUsername(username, pwd);
                JsonNode jsonUser = Json.toJson(user);
                return ok(jsonUser);
            }
        }
    }

    public static Result showUserAsJson(Long id){
        User user = User.find.byId(id);
        if (user==null) {
            return ok("No user available...");
        } else {
            JsonNode json = Json.toJson(user);
            return ok(json);
        }
    }

    public static Result createUser() {
        JsonNode jsonBody = request().body().asJson();
        if (jsonBody == null){
            return badRequest("Expecting Json data");
        } else {
            User createdUser = User.createUser(Json.fromJson(jsonBody, User.class));
            return ok(Json.toJson(createdUser));
        }
    }



    // NOT USED

    public static Result showUserAsJson(String username){
        User user = User.findUserByUsername(username, "secret");
        if (user==null) {
            return ok("No user available...");
        } else {
            JsonNode json = Json.toJson(user);
            return ok(json);
        }
    }


    public static Result getUser() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            String username = json.findPath("usernameF").asText();
            if (username == null) {
                return badRequest("Missing parameter [username]");
            } else {
                User user =  User.findUserByUsername(username);
                JsonNode jsonUser = Json.toJson(user);
                return ok(jsonUser);
            }
        }
    }


    @BodyParser.Of(BodyParser.Json.class)
    public static Result listUsersAsJson() {
        List<User> users = User.find.all();
        JsonNode json = Json.toJson(users);
        return ok(json);
    }

}
