package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    public User(){

    }

    public User(String username, String email, String fullName,String description, int score, Timestamp dateRegistration, String role, String password){
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.score = score;
        this.description = description;
        this.dateRegistration = dateRegistration;
        this.role = role;
        this.lastRegistration = dateRegistration;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;

    private String username;

    private String email;

    private String fullName;

    private int score;

    private String description;

    private Timestamp dateRegistration;

    private Timestamp lastRegistration;

    private String role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Timestamp dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public Timestamp getLastRegistration() {
        return lastRegistration;
    }

    public void setLastRegistration(Timestamp lastRegistration) {
        this.lastRegistration = lastRegistration;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
