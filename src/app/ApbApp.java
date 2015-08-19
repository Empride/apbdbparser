package app;

import app.controller.Controller;
import app.model.BaseModel;
import app.model.Model;
import app.view.BaseView;
import app.view.CompareView;

/**
 * Created by User on 19.08.2015.
 */
public class ApbApp {
    public static void main (String...args)
    {
        Controller controller=new Controller();

        BaseView baseView=new BaseView();
        CompareView compareView = new CompareView();

        Model model=new BaseModel();

        baseView.setController(controller);
        compareView.setController(controller);

        controller.setModel(model);
        controller.setCompareView(compareView);
        controller.setView(baseView);



    }
}
