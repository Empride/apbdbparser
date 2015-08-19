package app;

import app.controller.Controller;
import app.model.BaseModel;
import app.model.Model;
import app.view.BaseView;

/**
 * Created by User on 19.08.2015.
 */
public class ApbApp {
    public static void main (String...args)
    {
        Controller controller=new Controller();

        BaseView baseView=new BaseView();

        Model model=new BaseModel();

        baseView.setController(controller);

        controller.setModel(model);

        controller.setView(baseView);



    }
}
