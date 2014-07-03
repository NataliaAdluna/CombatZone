package pl.adluna.combatzone.view.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.adluna.combatzone.R;
import pl.adluna.combatzone.model.network.GetTrainingTask;

/**
 * Created by Natalia Stawowy on 01.07.14.
 */
public class TrainingActivity extends Activity {
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.training_table);

        try {
            GetTrainingTask getCords = new GetTrainingTask("http://www.adluna.webd.pl/combatzone_panel/selectaktualnosci.php") {
                @Override
                public void onPostExecute(JSONObject obj) {
                    try {
                        Context context = getApplicationContext();
                        CharSequence text = obj.getString("a");
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            getCords.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

 /*       List<String> news = new ArrayList<String>();
        news.add("trololo");
        news.add("jojojoj");
        Integer count=0;
        // while (cursor.moveToNext()) {

        String info = "20:30";

        TableRow tr = new TableRow(this);

        TextView labelDATE = new TextView(this);
        labelDATE.setText(date);
        labelDATE.setPadding(2, 0, 5, 0);
        labelDATE.setTextColor(Color.WHITE);
        labelDATE.setTextSize(30);
        tr.addView(labelDATE);

        TextView labelNEWS = new TextView(this);
        labelNEWS.setText(info);
        labelNEWS.setTextColor(Color.WHITE);
        labelNEWS.setTextSize(30);
        tr.addView(labelNEWS);

        // finally add this to the table row
        tableLayout.addView(tr, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        // }
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, pass);

            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select * from GRUPY");

            while(rs.next())
            {
                Context context = getApplicationContext();
                CharSequence text = rs.getString("GRUPA");
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }*/
    }

