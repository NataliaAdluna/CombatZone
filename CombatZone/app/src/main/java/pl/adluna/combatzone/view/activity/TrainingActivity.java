package pl.adluna.combatzone.view.activity;

import android.app.Activity;
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
public class TrainingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.training_table);

        List<String> news = new ArrayList<String>();
        news.add("trololo");
        news.add("jojojoj");
        Integer count=0;
        // while (cursor.moveToNext()) {
        String date = "Pon";
        String info = "20:30";

        TableRow tr = new TableRow(this);

        TextView labelDATE = new TextView(this);
        labelDATE.setText(date);
        labelDATE.setPadding(2, 0, 5, 0);
        tr.addView(labelDATE);

        TextView labelNEWS = new TextView(this);
        labelNEWS.setText(info);
        tr.addView(labelNEWS);

        // finally add this to the table row
        tableLayout.addView(tr, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        // }
    }
}
