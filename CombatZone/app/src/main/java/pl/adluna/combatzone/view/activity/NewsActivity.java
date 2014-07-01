package pl.adluna.combatzone.view.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.adluna.combatzone.R;

/**
 * Created by Natalia Stawowy on 01.07.14.
 */
public class NewsActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.news_table);

        List<String> news = new ArrayList<String>();
        news.add("trololo");
        news.add("jojojoj");

       // while (cursor.moveToNext()) {
            String date = "11.03";
            String info = "Dzisiaj wznawiamy treningi";

            TableRow tr = new TableRow(this);

            TextView labelDATE = new TextView(this);
            labelDATE.setText(date);
            labelDATE.setPadding(2, 0, 5, 0);
            labelDATE.setTextColor(Color.WHITE);
            tr.addView(labelDATE);

            TextView labelNEWS = new TextView(this);
            labelNEWS.setText(info);
            labelNEWS.setTextColor(Color.WHITE);
            tr.addView(labelNEWS);

            // finally add this to the table row
            tableLayout.addView(tr, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
       // }
    }
}
