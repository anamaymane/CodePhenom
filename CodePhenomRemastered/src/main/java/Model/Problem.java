package Model;


import jdk.internal.loader.AbstractClassLoaderValue;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long problemId;

    private String name;

    private String type;

    private String problemTitle;

    private String problemText;

    private int memlimit;

    private int timelimit;

    private int difficulty;

    private Timestamp dateAjout;

    private String visible;

    private int solved;

    @ElementCollection
    private List<Commentary> commentaries = new ArrayList<Commentary>();

    @OneToMany(mappedBy = "problem")
    private List<Submission> submissions = new ArrayList<Submission>();

    @Embeddable
    public class Commentary {

        private User user;
        Timestamp date;
        String content;
    }


}

