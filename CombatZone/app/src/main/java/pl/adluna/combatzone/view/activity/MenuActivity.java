package pl.adluna.combatzone.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.lang.reflect.Array;

import pl.adluna.combatzone.R;
import pl.adluna.combatzone.model.News;
import pl.adluna.combatzone.model.Training;

/**
 * Created by Natalia Stawowy on 01.07.14.
 */
public class MenuActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button news = (Button) findViewById(R.id.news);
        Button facebook =(Button) findViewById(R.id.facebook);
        Button training = (Button) findViewById(R.id.training);
        Button fighterstore =(Button) findViewById(R.id.fighterstore);
        Button page = (Button) findViewById(R.id.page);
        Button seminar =(Button) findViewById(R.id.seminar);

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
                Intent intent = new Intent(view.getContext(),News.class);//todo
                startActivityForResult(intent,0);
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
                Intent intent = new Intent(view.getContext(),News.class); //todo
                startActivityForResult(intent,0);
            }
        });

        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),News.class); //todo
                startActivityForResult(intent,0);
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
