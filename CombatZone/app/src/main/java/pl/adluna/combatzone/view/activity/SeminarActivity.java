package pl.adluna.combatzone.view.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pl.adluna.combatzone.R;
import pl.adluna.combatzone.model.News;
import pl.adluna.combatzone.model.Seminar;
import pl.adluna.combatzone.model.network.GetNewsTask;
import pl.adluna.combatzone.model.network.GetSeminarTask;

/**
 * Created by Natalia Stawowy on 01.07.14.
 */
public class SeminarActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seminar);

        GetSeminarTask getSeminarTask = new GetSeminarTask(getString(R.string.seminar)) {
            @Override
            public void onPostExecute(JSONObject obj) {
                JSONArray jArray = new JSONArray();
                Seminar seminar = new Seminar();
                try {
                    jArray = obj.getJSONArray(getString(R.string.seminarLabel));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < jArray.length(); i++) {
                    try {
                        JSONObject object = jArray.getJSONObject(i);

                        seminar.setDescription(object.get(getString(R.string.description)).toString());
                        seminar.setFoto(object.get(getString(R.string.foto)).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                String description = seminar.getDescription();
                String foto =seminar.getFoto();

                TextView textView = (TextView) findViewById(R.id.seminar_text);
                textView.setText(description);
                textView.setTextColor(Color.BLACK);

                ImageView imageView = (ImageView) findViewById(R.id.picture);
                byte[] decodedString = Base64.decode(foto, Base64.DEFAULT);
                Bitmap decode = BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
                imageView.setImageBitmap(decode);

            }
        };
        getSeminarTask.execute();

        new GetSeminarTask(getString(R.string.seminar)).execute();



    }

}
