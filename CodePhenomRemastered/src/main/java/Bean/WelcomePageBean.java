package Bean;

import Dao.AnnouncementDao;
import Model.Announcement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "welcomePageBean")
@RequestScoped
public class WelcomePageBean {

    private List<Announcement> announcements;

    public List<Announcement> getAnnouncements() throws ClassNotFoundException {
        List<Announcement> announcements = new AnnouncementDao().getAnnouncement();
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }
}
