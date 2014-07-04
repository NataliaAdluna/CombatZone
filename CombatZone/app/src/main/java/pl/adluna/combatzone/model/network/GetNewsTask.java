package pl.adluna.combatzone.model.network;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natalia Stawowy on 02.07.14.
 */
public class GetNewsTask extends AsyncTask<String, String, JSONObject> {
    private HttpClient httpClient;
    private HttpPost httpPost;
    private String url;

    public GetNewsTask(String url) {
        this.url = url;
    }

    /**
     * Login authorization
     */
    @Override
    protected JSONObject doInBackground(String... arg0) {
        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost(url);
        HttpResponse httpResponse;
        BufferedReader br = null;
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("login", "admin"));
            nameValuePairs.add(new BasicNameValuePair("haslo", "admin"));
            /**
             * Http post to server
             */
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpResponse = httpClient.execute(httpPost);
            int status = httpResponse.getStatusLine().getStatusCode();

            if (status==200) {
                br = new BufferedReader(new InputStreamReader(httpResponse
                        .getEntity().getContent()));
                return new JSONObject(br.readLine());
            } else {
                Log.e("Login error", "Login error");
            }
        } catch (Exception e) {
            Log.e("HTTP Failed", e.toString());
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }
}
