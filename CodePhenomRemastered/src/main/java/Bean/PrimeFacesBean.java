package Bean;





import Dao.ProblemDao;
import Dao.UserDao;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
            ArrayList<HashMap<String,String>> types = new ProblemDao().getProblemsCategoriesCount();

            for(HashMap<String,String> type: types){

                model.set(type.get("category"), Integer.valueOf(type.get("count")));
            }


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
