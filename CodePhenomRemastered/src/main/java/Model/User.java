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
    List<Submission> sumissions = new ArrayList<Submission>();


}
