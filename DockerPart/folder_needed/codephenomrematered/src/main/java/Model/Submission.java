package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long submissionId;

    private String problemId;

    private String codeSource;

    private String username;

    private Timestamp dateSubmission;

    private String languageName;

    private String memoryResult;

    private String timeResult;

    private String verdict;

    private String type;

    private String totalVerdict;

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }


    public String getCodeSource() {
        return codeSource;
    }

    public void setCodeSource(String codeSource) {
        this.codeSource = codeSource;
    }


    public Timestamp getDateSubmission() {
        return dateSubmission;
    }

    public void setDateSubmission(Timestamp dateSubmission) {
        this.dateSubmission = dateSubmission;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getMemoryResult() {
        return memoryResult;
    }

    public void setMemoryResult(String memoryResult) {
        this.memoryResult = memoryResult;
    }

    public String getTimeResult() {
        return timeResult;
    }

    public void setTimeResult(String timeResult) {
        this.timeResult = timeResult;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotalVerdict() {
        return totalVerdict;
    }

    public void setTotalVerdict(String totalVerdict) {
        this.totalVerdict = totalVerdict;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
