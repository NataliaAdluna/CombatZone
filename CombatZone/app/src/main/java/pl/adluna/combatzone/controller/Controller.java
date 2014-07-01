package pl.adluna.combatzone.controller;

import android.app.Application;
import android.content.Context;

/**
 * Created by Natalia Stawowy on 01.07.14.
 */
public class Controller extends Application{
    private Context context;
    private static Controller controller;
    public static Controller getInstance(){
        return controller;
    }

    public Context getContext() {
        return context;
    }

    public static Controller getController() {
        return controller;
    }

    public static void setController(Controller controller) {
        Controller.controller = controller;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        controller=this;
        this.context = getApplicationContext();
    }
}
