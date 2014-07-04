package pl.adluna.combatzone.view.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pl.adluna.combatzone.R;
import pl.adluna.combatzone.controller.Controller;
import pl.adluna.combatzone.model.News;
import pl.adluna.combatzone.model.network.GetNewsTask;

/**
 * Created by Natalia Stawowy on 01.07.14.
 */
public class NewsActivity extends Activity {
    final List<News> newsList = new ArrayList<News>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.news_table);
        final Context con = this;
        GetNewsTask getNewsTask = new GetNewsTask("http://www.adluna.webd.pl/combatzone_panel/selectaktualnosci.php") {
            @Override
            public void onPostExecute(JSONObject obj) {
                JSONArray jArray = new JSONArray();
                try {
                    jArray = obj.getJSONArray("news");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < jArray.length(); i++) {
                    try {
                        JSONObject object = jArray.getJSONObject(i);
                        News news = new News();
                        news.setDate(object.get("data").toString().substring(9,10)+"."+object.get("data").toString().substring(5,7));
                        news.setText(object.get("text").toString());
                        newsList.add(news);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                for (News news : newsList) {
                    String date = news.getDate();
                    String info = news.getText();

                    TableRow tr = new TableRow(con);

                    TextView labelDATE = new TextView(con);
                    labelDATE.setText(date);
                    labelDATE.setPadding(0, 0, 6, 0);
                    labelDATE.setTextColor(Color.BLACK);
                    labelDATE.setTextSize(15);
                    labelDATE.setEllipsize(null);
                    labelDATE.setMaxLines(2);
                    labelDATE.setSingleLine(false);
                    tr.addView(labelDATE);

                    TextView labelNEWS = new TextView(con);

                    labelNEWS.setText(info);
                    labelNEWS.setTextColor(Color.BLACK);
                    labelNEWS.setTextSize(15);
                    labelNEWS.setEllipsize(null);
                    labelNEWS.setMaxLines(2);
                    labelNEWS.setSingleLine(false);
                    tr.addView(labelNEWS);

                    tableLayout.addView(tr, new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        };
        getNewsTask.execute();

        new GetNewsTask("http://www.adluna.webd.pl/combatzone_panel/selectaktualnosci.php").execute();



    }
}