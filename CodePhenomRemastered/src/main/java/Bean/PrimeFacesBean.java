package Bean;





import Dao.AnnouncementDao;
import Dao.ProblemDao;
import Dao.UserDao;
import Model.Announcement;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@ManagedBean(name = "PrimeFacesBean")
public class PrimeFacesBean implements Serializable {

    private PieChartModel model;
    private List<String> images;
    private List<String> usedProgrammingLanguages = new ArrayList<String>();

    @PostConstruct
    public void init(){
        images = new ArrayList<java.lang.String>();
        for (int i = 1; i <= 4; i++) {
            images.add("" + i);
        }
        createPieModel();

    }

    public List<String> languagesUsedByUser(String username){
        try {
            usedProgrammingLanguages = new UserDao().listOfProgrammingLanguagesUsedPerUser(username);
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
        return  usedProgrammingLanguages;
    }

    public PieChartModel getChartPieCategoryAvailable() {
        return model;
    }


    public List<java.lang.String> getImages() {
        return images;
    }

    public List<Announcement> getAnnouncements() throws ClassNotFoundException {
        return new AnnouncementDao().getAnnouncement();
    }

    private void  createPieModel() {
        try {
            model = new PieChartModel();
            ChartData data = new ChartData();

            ArrayList<HashMap<String,String>> types = new ProblemDao().getProblemsCategoriesCount();
            PieChartDataSet dataSet = new PieChartDataSet();
            List<Number> values = new ArrayList<>();
            List<String> bgColors = new ArrayList<>();
            for(HashMap<String,String> type: types){
                int r = ThreadLocalRandom.current().nextInt(0, 255 + 1);
                int g = ThreadLocalRandom.current().nextInt(0, 255 + 1);
                int b = ThreadLocalRandom.current().nextInt(0, 255 + 1);
                values.add(Integer.valueOf(type.get("count")));
                bgColors.add("rgb(" + r + "," + g + "," + b + ")");
            }
            dataSet.setBackgroundColor(bgColors);
            dataSet.setData(values);
            data.addChartDataSet(dataSet);

            List<String> labels = new ArrayList<>();
            for(HashMap<String,String> type: types){
                labels.add(type.get("category"));
            }
            data.setLabels(labels);
            model.setData(data);

        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
    }
}
