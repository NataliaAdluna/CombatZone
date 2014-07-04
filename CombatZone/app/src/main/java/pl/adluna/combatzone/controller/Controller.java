package pl.adluna.combatzone.controller;

import android.app.Application;
import android.content.Context;

import java.util.List;

import pl.adluna.combatzone.model.News;

/**
 * Created by Natalia Stawowy on 01.07.14.
 */
public class Controller extends Application{
    private Context context;
    private static Controller controller;
    private List<News> newsList;

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

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
