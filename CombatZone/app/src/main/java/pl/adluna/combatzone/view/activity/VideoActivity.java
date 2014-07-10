package pl.adluna.combatzone.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewSwitcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pl.adluna.combatzone.R;
import pl.adluna.combatzone.model.network.GetGalleryTask;

/**
 * Created by Natalia Stawowy on 07.07.14.
 */
public class VideoActivity extends Activity  {
    final Context context = this;
    final List<Drawable> pictureList = new ArrayList<Drawable>();
    final List<String> movieList = new ArrayList<String>();
    final List<String> descriptionList = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        CharSequence text = getString(R.string.loadingVideo);
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        GetGalleryTask getGalleryTask = new GetGalleryTask(getString(R.string.video)) {
            @Override
            public void onPostExecute(JSONObject obj) {
                JSONArray jArray = new JSONArray();
                try {
                    jArray = obj.getJSONArray(getString(R.string.videoLabel));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < jArray.length(); i++) {
                    try {
                        JSONObject object = jArray.getJSONObject(i);
                        pictureList.add(convert(object.get(getString(R.string.img)).toString()));
                        movieList.add(object.get(getString(R.string.link)).toString());
                        descriptionList.add(object.get(getString(R.string.title)).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Context context = getApplicationContext();

                final TableLayout table = (TableLayout) findViewById(R.id.video_table);
                int counter=0;
               for(String url:movieList){

                    TableRow tr = new TableRow(context);


                    tr.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));


                   ImageView im = new ImageView (context);
                   //im.setImageDrawable(getResources().getDrawable(R.drawable.youtube));
                   //im.setPadding(5, 10, 0, 10);
                   im.setImageDrawable(pictureList.get(counter));
                   tr.addView(im, 150,110);



                   TextView labelTitle = new TextView(context);
                   labelTitle.setPadding(5, 0, 0, 0);
                    labelTitle.setText(descriptionList.get(counter));
                    labelTitle.setTextColor(Color.BLACK);
                    labelTitle.setTextSize(15);
                    labelTitle.setEllipsize(null);
                    labelTitle.setMaxLines(3);
                    labelTitle.setSingleLine(false);
                    tr.addView(labelTitle);

                    tr.setPadding(5,15,5,15);
                   final int finalCounter = counter;
                   tr.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Uri uri = Uri.parse(movieList.get(finalCounter));
                           Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                           startActivity(intent);
                       }
                   });
                    table.addView(tr, new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                   counter++;
                }

            }
        };
        getGalleryTask.execute();

    }

    private Drawable convert(String url){

        byte[] decodedString = Base64.decode(url, Base64.DEFAULT);
        Bitmap decode = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Drawable d = new BitmapDrawable(getResources(),decode);
        return d;
    }
}
