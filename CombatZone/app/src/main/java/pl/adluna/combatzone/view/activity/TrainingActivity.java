package pl.adluna.combatzone.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
    private static final String url = "jdbc:mysql://192.168.1.118:3306/adluna_combatzone";
    private static final String user = "adluna_combatzon";
    private static final String pass = "combatzone2014";
    public String testDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, pass);

            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //ResultSet rs = st.executeQuery("select * from tableTest");
            // ResultSetMetaData rsmd = rs.getMetaData();
            return con.toString();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.training_table);

        String date = testDB();

        List<String> news = new ArrayList<String>();
        news.add("trololo");
        news.add("jojojoj");
        Integer count=0;
        // while (cursor.moveToNext()) {

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
