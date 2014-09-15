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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import pl.adluna.combatzone.R;
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
        GetNewsTask getNewsTask = new GetNewsTask(getString(R.string.news)) {
            @Override
            public void onPostExecute(JSONObject obj) {
                JSONArray jArray = new JSONArray();
                try {
                    jArray = obj.getJSONArray(getString(R.string.newsLabel));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < jArray.length(); i++) {
                    try {
                        JSONObject object = jArray.getJSONObject(i);
                        News news = new News();
                        news.setStringDate(object.get(getString(R.string.dateLabel)).toString().substring(8,10)+"."+object.get(getString(R.string.dateLabel)).toString().substring(5,7));
                        news.setDate(new Date(Integer.parseInt(object.get(getString(R.string.dateLabel)).toString().substring(0,4)),
                                Integer.parseInt(object.get(getString(R.string.dateLabel)).toString().substring(5,7)),
                                Integer.parseInt(object.get(getString(R.string.dateLabel)).toString().substring(0,4))));
                        news.setText(object.get(getString(R.string.text)).toString());
                        newsList.add(news);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                Collections.sort(newsList, new Comparator<News>() {
                    @Override
                    public int compare(News news, News news2) {
                        return (int) (news.getDate().getTime()-news2.getDate().getTime());
                    }
                });


                for (News news : newsList) {
                    String date = news.getStringDate();
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
                    labelNEWS.setPadding(0,0,0,10);
                    tr.addView(labelNEWS);

                    tableLayout.addView(tr, new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        };
        getNewsTask.execute();

        new GetNewsTask(getString(R.string.news)).execute();



    }
}