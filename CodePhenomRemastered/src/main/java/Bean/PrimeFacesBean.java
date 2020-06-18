package Bean;





import Dao.ProblemDao;
import Dao.UserDao;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "PrimeFacesBean")
public class PrimeFacesBean implements Serializable {

    private PieChartModel model;
    private List<String> images;

    @PostConstruct
    public void init(){
        images = new ArrayList<java.lang.String>();
        for (int i = 1; i <= 4; i++) {
            images.add("" + i);
        }
        createPieModel();
    }

    public PieChartModel getChartPieCategoryAvailable() {
        return model;
    }


    public List<java.lang.String> getImages() {
        return images;
    }

    private void  createPieModel() {
        try {
            model = new PieChartModel();
            //List<Object> categories = new ProblemDao().getProblemsCategories();
            model.set("Java", 62);//jobs in thousands
            model.set("Python", 46);
            model.set("JavaScript", 38);
            model.set("C++", 31);
            model.set("C#", 27);
            model.set("PHP", 14);
            model.set("Perl", 14);

            //followings are some optional customizations:
            //set title
            model.setTitle("2018 Jobs for top languages");
            //set legend position to 'e' (east), other values are 'w', 's' and 'n'
            model.setLegendPosition("e");
            //enable tooltips
            model.setShowDatatip(true);
            //show labels inside pie chart
            model.setShowDataLabels(true);
            //show label text  as 'value' (numeric) , others are 'label', 'percent' (default). Only one can be used.
            model.setDataFormat("value");
            //format: %d for 'value', %s for 'label', %d%% for 'percent'
            model.setDataLabelFormatString("%dK");
            //pie sector colors
            model.setSeriesColors("aaf,afa,faa,ffa,aff,faf,ddd");
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
    }
}
