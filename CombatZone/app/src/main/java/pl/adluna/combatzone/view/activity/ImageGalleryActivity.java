package pl.adluna.combatzone.view.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pl.adluna.combatzone.R;
import pl.adluna.combatzone.model.Hours;
import pl.adluna.combatzone.model.Seminar;
import pl.adluna.combatzone.model.network.GetGalleryTask;
import pl.adluna.combatzone.model.network.GetSeminarTask;

/**
 * Created by Natalia Stawowy on 07.07.14.
 */
public class ImageGalleryActivity extends Activity implements
    AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {
    final ViewSwitcher.ViewFactory vf = this;
    final Context context = this;
    final AdapterView.OnItemSelectedListener l = this;
    final List<Drawable> pictureList = new ArrayList<Drawable>();
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.pictures);
            CharSequence text = getString(R.string.loading);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            GetGalleryTask getGalleryTask = new GetGalleryTask(getString(R.string.gallery)) {
                @Override
                public void onPostExecute(JSONObject obj) {
                    JSONArray jArray = new JSONArray();
                    try {
                        jArray = obj.getJSONArray(getString(R.string.galleryLabel));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < jArray.length(); i++) {
                        try {
                            JSONObject object = jArray.getJSONObject(i);
                            pictureList.add(convert(object.get(getString(R.string.foto)).toString()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    Context context = getApplicationContext();


                    switcher = (ImageSwitcher) findViewById(R.id.picture_switcher);
                    switcher.setFactory(vf);
                    switcher.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in ));
                    switcher.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out));

                    Gallery g = (Gallery) findViewById(R.id.gallery);
                    g.setAdapter(new ImageAdapter(context));
                    g.setOnItemSelectedListener(l);

                }
            };
            getGalleryTask.execute();

        }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switcher.setImageDrawable(pictureList.get(position));
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    public View makeView() {
        new GetGalleryTask(getString(R.string.gallery)).execute();
        final ImageView i = new ImageView(this);

        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));


        return i;
    }

    private ImageSwitcher switcher;

    public class ImageAdapter extends BaseAdapter {
        public ImageAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return pictureList.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            final ImageView i = new ImageView(context);

            i.setImageDrawable(pictureList.get(position));
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new Gallery.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return i;
        }

        private Context context;

    }

    private Drawable convert(String url){

        byte[] decodedString = Base64.decode(url, Base64.DEFAULT);
        Bitmap decode = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Drawable d = new BitmapDrawable(getResources(),decode);
        return d;
    }
}
