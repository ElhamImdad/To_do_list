package com.example.smoot.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import android.view.Menu;


public class MainActivity extends AppCompatActivity {
    private TextView textCardView;
    private RequestQueue mQueue;
    ArrayList<TrelloCard> trelloListCard = new ArrayList<>();
    ArrayList<String> cardsTitle = new ArrayList<>();


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mQueue = Volley.newRequestQueue(this);

        // call json method
        jsonParse();

        adapter = new RecycleAdapter(cardsTitle);

        recyclerView.setAdapter(adapter);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private void jsonParse() {
        final String url = "https://trello.com/1/boards/ZqN99gGN/lists?cards=open&card_fields=name&filter=open&fields=name";
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("jsonarray", response.toString());
                        try {
                            JSONObject boardlists = response.getJSONObject(0);
                            Log.e("jsonarray", boardlists.toString());
                            TrelloCard trello = new TrelloCard();

                            for (int i = 0; i < boardlists.length(); i++) {
                                JSONObject list = response.getJSONObject(i);
                                String listname = list.getString("name");
                                if (listname.equals("Backlog")) {
                                    JSONArray cards = list.getJSONArray("cards");
                                    for (int e = 0; e < cards.length(); e++) {
                                        String name = cards.getJSONObject(e).getString("name");
                                        cardsTitle.add(name);
                                        Log.e("card title", name);
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException excep) {
                            excep.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        mQueue.add(request);
    }
}
