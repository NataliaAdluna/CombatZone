package pl.adluna.combatzone.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import pl.adluna.combatzone.R;


/**
 * Created by Natalia Stawowy on 01.07.14.
 */
public class MenuActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        ImageView news = (ImageView) findViewById(R.id.news);
        ImageView facebook =(ImageView) findViewById(R.id.facebook);
        ImageView training = (ImageView) findViewById(R.id.training);
        ImageView fighterstore =(ImageView) findViewById(R.id.fighterstore);
        ImageView page = (ImageView) findViewById(R.id.page);
        ImageView seminar =(ImageView) findViewById(R.id.seminar);

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),NewsActivity.class);
                startActivityForResult(intent,0);

            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(getString(R.string.facebook));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),TrainingActivity.class);
                startActivityForResult(intent,0);
            }
        });

        fighterstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(getString(R.string.fightershop));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(getString(R.string.combatzone));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        seminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),SeminarActivity.class);
                startActivityForResult(intent,0);
            }
        });

    }
}
