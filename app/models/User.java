package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Arrays.*;
import java.util.List;

/**
 * @author : Helena Hjertén
 */

@Entity
public class User extends Model {

    @Id
    public Long id;
    public String username;
    public String emailAddress;
    public String password;
    public String firstName;
    public String lastName;
    public String dateOfBirth;

    public String streetAddress;
    public String zipCode;
    public String city;
    public List<String> interests;
    public boolean swedish;


    public User(String username,
                String emailAddress,
                String password,
                String firstName,
                String lastName,
                String dateOfBirth,
                String streetAddress,
                String zipCode,
                String city,
                List<String> interests,
                boolean swedish) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.swedish = swedish;

    }

    public static Finder<Long, User> find =
            new Finder<Long, User>(Long.class, User.class);

    public static User findUserByUsername(String username){
        return find.where()
                .eq("username", username)
                .findUnique();
    }


    public static User findUserByUsername2(String username, String password){
        return find.where()
                .eq("username", username)
                .eq("password", password)
                .findUnique();
    }

    public static User authenticate(String username, String password) {
        return find.where().eq("username", username)
                .eq("password", password).findUnique();
    }

    public static User createUser(User u){
        User createdUser =  new User(u.username,u.emailAddress, u.password,u.firstName, u.lastName,
                u.dateOfBirth,u.streetAddress, u.zipCode, u.city, u.interests, u.swedish);
        createdUser.save();
        return createdUser;
    }

}
