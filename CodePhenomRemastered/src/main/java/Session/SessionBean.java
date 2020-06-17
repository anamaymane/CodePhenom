package Session;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean(name = "sessionBean")
@ApplicationScoped
public class SessionBean implements Serializable {

    public static HttpSession session = null;

    public HttpSession getSession() {
        if(session == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            session = (HttpSession) facesContext.getExternalContext().getSession(false);
        }
        return (HttpSession) session;
    }

}
