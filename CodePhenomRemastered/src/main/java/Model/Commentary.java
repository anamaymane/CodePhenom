package Model;

import javax.persistence.Embeddable;
import java.sql.Timestamp;

@Embeddable
public class Commentary {

    private String username;
    private Timestamp date;
    private String content;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}