package Model;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long submissionId;

    @ManyToOne
    private Problem problem;

    String codeSource;

    @ManyToOne
    User user;

    Timestamp dateSubmission;

    String languageName;

    String memoryResult;

    String timeResult;

    String verdict;

    String type;

    String totalVerdict;
}
