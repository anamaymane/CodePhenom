package Model;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;

    private String username;

    private String email;

    private String fullName;

    private String score;

    private String description;

    private Timestamp dateRegistration;

    private Timestamp lastRegistration;

    private String role;

    @OneToMany(mappedBy = "user")
    private List<Submission> submissions = new ArrayList<Submission>();

    @OneToMany(mappedBy = "sender")
    private List<Message> messagesSent = new ArrayList<Message>();

    @OneToMany(mappedBy = "receiver")
    private List<Message> messagedReceived = new ArrayList<Message>();

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
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

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public List<Message> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(List<Message> messagesSent) {
        this.messagesSent = messagesSent;
    }

    public List<Message> getMessagedReceived() {
        return messagedReceived;
    }

    public void setMessagedReceived(List<Message> messagedReceived) {
        this.messagedReceived = messagedReceived;
    }
}
