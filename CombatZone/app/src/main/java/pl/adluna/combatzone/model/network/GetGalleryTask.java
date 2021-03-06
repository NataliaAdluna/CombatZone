package pl.adluna.combatzone.model.network;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Natalia Stawowy on 07.07.14.
 */
public class GetGalleryTask extends AsyncTask<String, String, JSONObject> {
    private HttpClient httpClient;
    private HttpPost httpPost;
    private String url;

    public GetGalleryTask(String url) {
        this.url = url;
    }

    @Override
    protected JSONObject doInBackground(String... arg0) {
        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost(url);
        HttpResponse httpResponse;
        BufferedReader br = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            int status = httpResponse.getStatusLine().getStatusCode();

            if (status==200) {
                br = new BufferedReader(new InputStreamReader(httpResponse
                        .getEntity().getContent()));
                return new JSONObject(br.readLine());
            } else {
                Log.e("Error", "Error");
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
