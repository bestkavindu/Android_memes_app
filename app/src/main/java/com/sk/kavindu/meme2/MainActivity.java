package com.sk.kavindu.meme2;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mrecycleview;
    private meme_adapter mmeme_adapter;
    private ArrayList<meme_item> mmeme_list;
    private RequestQueue mrequestqueue;

    public SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mrecycleview = findViewById(R.id.recycleview);
        mrecycleview.setHasFixedSize(true);
        mrecycleview.setLayoutManager(new LinearLayoutManager(this));
        mmeme_list = new ArrayList<>();
        mrequestqueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        String url = "http://kavi-api.000webhostapp.com/api/collections/get/memedb";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("entries");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);


                                String title = hit.getString("date");
                                JSONObject imgObj = hit.getJSONObject("image");
                                String imageUrl = imgObj.getString("path");
                                String fullurl = "http://kavi-api.000webhostapp.com/" + imageUrl;

                                mmeme_list.add(new meme_item(fullurl, title));
                                Collections.sort(mmeme_list, Collections.reverseOrder());
                                //Collections.reverse(mmeme_list);
                            }


                            mmeme_adapter = new meme_adapter(MainActivity.this, mmeme_list);
                            // Collections.sort(mmeme_list, Collections.reverseOrder());

                            mrecycleview.setAdapter(mmeme_adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mrequestqueue.add(request);


    }

    @Override
    public void onRefresh() {

    }
}
