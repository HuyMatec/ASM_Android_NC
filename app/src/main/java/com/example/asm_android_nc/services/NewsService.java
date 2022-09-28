package com.example.asm_android_nc.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class NewsService extends IntentService {
    private AsyncHttpClient client = new SyncHttpClient();
    private final String url = "";
    public NewsService() {
        super("NewsService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null){
            client.get(this, url, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        ArrayList<ItemModel> items = new ArrayList<>();
//                        JSONArray array = response.getJSONArray("items");
                        for (int i = 0; i < response.length(); i++){
                            JSONObject object = response.getJSONObject(i);
                            String creation_date = object.getString("creation_date");
                            String title = object.getString("title");
                            ItemModel model = new ItemModel(creation_date, title);
                            items.add(model);
                            Log.d(">>>>>>TAG", "onSuccess: " + title);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public class ItemModel{
        private String creation_date;
        private String title;

        public ItemModel(String creation_date, String title) {
            this.creation_date = creation_date;
            this.title = title;
        }

        public String getCreation_date() {
            return creation_date;
        }

        public void setCreation_date(String creation_date) {
            this.creation_date = creation_date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
