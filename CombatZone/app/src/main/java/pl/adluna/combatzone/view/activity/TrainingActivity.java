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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.adluna.combatzone.R;
import pl.adluna.combatzone.model.Hours;
import pl.adluna.combatzone.model.News;
import pl.adluna.combatzone.model.Training;
import pl.adluna.combatzone.model.TrainingTable;
import pl.adluna.combatzone.model.network.GetNewsTask;
import pl.adluna.combatzone.model.network.GetTrainingTask;

/**
 * Created by Natalia Stawowy on 01.07.14.
 */
public class TrainingActivity extends Activity {
    final TrainingTable trainingTable = new TrainingTable();

    private String[] days = {"Pon", "Wt", "Sr", "Czw", "Pt", "Sob", "Nd"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training);
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.training_table);
        final Context con = this;
        GetTrainingTask getTrainingTask = new GetTrainingTask("http://www.adluna.webd.pl/combatzone_panel/selecttreningi.php") {
            @Override
            public void onPostExecute(JSONObject obj) {
                JSONArray jArray = new JSONArray();
                try {
                    jArray = obj.getJSONArray("treningi");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

               final Map<String,Hours> hours = trainingTable.jsonToTable(jArray);
                TableRow tr = new TableRow(con);
                TextView labelDATE1 = new TextView(con);
                labelDATE1.setText("");
                labelDATE1.setTextColor(Color.BLACK);
                labelDATE1.setTextSize(15);
                labelDATE1.setEllipsize(null);
                labelDATE1.setMaxLines(2);
                labelDATE1.setSingleLine(false);
                tr.addView(labelDATE1);

                TextView labelHOUR1 = new TextView(con);
                labelHOUR1.setText("Gr. 1");
                labelHOUR1.setTextColor(Color.BLACK);
                labelHOUR1.setTextSize(15);
                labelHOUR1.setEllipsize(null);
                labelHOUR1.setMaxLines(2);
                labelHOUR1.setSingleLine(false);
                tr.addView(labelHOUR1);

                TextView labelHOUR2 = new TextView(con);
                labelHOUR2.setText("Gr. 2");
                labelHOUR2.setTextColor(Color.BLACK);
                labelHOUR2.setTextSize(15);
                labelHOUR2.setEllipsize(null);
                labelHOUR2.setMaxLines(2);
                labelHOUR2.setSingleLine(false);
                tr.addView(labelHOUR2);
                tableLayout.addView(tr, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.WRAP_CONTENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
               for (int i=0; i<7; i++) {
                   if(hours.get(days[i])!=null){
                       if(!(hours.get(days[i]).getHour1()==""&&hours.get(days[i]).getHour2()=="")) {
                           String date = days[i];
                           String hour1 = hours.get(days[i]).getHour1();
                           String hour2 = hours.get(days[i]).getHour2();

                           tr = new TableRow(con);

                           labelDATE1 = new TextView(con);
                           labelDATE1.setText(date);
                           labelDATE1.setTextColor(Color.BLACK);
                           labelDATE1.setTextSize(15);
                           labelDATE1.setEllipsize(null);
                           labelDATE1.setMaxLines(2);
                           labelDATE1.setSingleLine(false);
                           tr.addView(labelDATE1);

                           labelHOUR1 = new TextView(con);
                           labelHOUR1.setText(hour1);
                           labelHOUR1.setTextColor(Color.BLACK);
                           labelHOUR1.setTextSize(15);
                           labelHOUR1.setEllipsize(null);
                           labelHOUR1.setMaxLines(2);
                           labelHOUR1.setSingleLine(false);
                           tr.addView(labelHOUR1);

                           labelHOUR2 = new TextView(con);
                           labelHOUR2.setText(hour2);
                           labelHOUR2.setTextColor(Color.BLACK);
                           labelHOUR2.setTextSize(15);
                           labelHOUR2.setEllipsize(null);
                           labelHOUR2.setMaxLines(2);
                           labelHOUR2.setSingleLine(false);
                           tr.addView(labelHOUR2);

                           tableLayout.addView(tr, new TableLayout.LayoutParams(
                                   TableLayout.LayoutParams.WRAP_CONTENT,
                                   TableLayout.LayoutParams.WRAP_CONTENT));
                       }

                   }
               }
            }
        };
        getTrainingTask.execute();
        new GetTrainingTask("http://www.adluna.webd.pl/combatzone_panel/selecttreningi.php");
    }
}