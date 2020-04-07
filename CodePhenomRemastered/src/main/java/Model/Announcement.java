package Model;

import org.hibernate.annotations.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long announcementId;

    private String title;

    private String body;

    private Timestamp dateAnnouncement;

    public long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(long announcementId) {
        this.announcementId = announcementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getDateAnnouncement() {
        return dateAnnouncement;
    }

    public void setDateAnnouncement(Timestamp dateAnnouncement) {
        this.dateAnnouncement = dateAnnouncement;
    }
}
