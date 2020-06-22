package Model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Message {

    public Message(){

    }

    public Message(String receiver, String sender, String content, String object , Timestamp date){

        this.receiver = receiver;
        this.sender = sender;
        this.content = content;
        this.object = object;
        this.date = date;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;

    private String object;

    private String content;

    private Timestamp date;

    private String sender;

    private String receiver;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
