package Model;

import javax.persistence.Embeddable;
import java.sql.Timestamp;

@Embeddable
public class Commentary {

    public Commentary(){

    }

    private String username;
    private Timestamp date;
    private String content;

    public Commentary(String username,Timestamp date, String content){
        this.username =username;
        this.date = date;
        this.content =content;
    }

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