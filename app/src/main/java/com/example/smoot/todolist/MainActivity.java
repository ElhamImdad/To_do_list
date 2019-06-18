package com.example.smoot.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {
    private TextView textCardView;
    private RequestQueue mQueue;
    ArrayList<TrelloCard> trelloListCard = new ArrayList<>();
    RecyclerView recyclerView ;
    RecyclerView.Adapter adapter ;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        textCardView = (TextView) findViewById(R.id.toDoText);
        mQueue = Volley.newRequestQueue(this);
         // call json method
        jsonParse();

        adapter = new RecycleAdapter(trelloListCard);
        recyclerView.setAdapter(adapter);
/*
        Button button_addCard = (Button)findViewById(R.id.addCards);
        button_addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = (EditText) findViewById(R.id.simpleEditText);
                String editTextValue = title.getText().toString();
            }
        });
        */
    }

    private void jsonParse(){
        final String url = "https://trello.com/1/boards/ZqN99gGN/lists?cards=open&card_fields=name&filter=open&fields=name";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray(url);
                        //    JSONObject id = response.getJSONObject("id");
                        //    JSONObject name = response.getJSONObject("name");


                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject results = jsonArray.getJSONObject(i);

                                String id = results.getString("id");
                             //   String name = results.getString("name");
                                TrelloCard t = new TrelloCard();
                                t.setId(id);
                                trelloListCard.add(t);
                                Log.d("Result" , id);
                                textCardView.append(String.valueOf(id)+ "\n\n ");

                               // textCardView.append(String.valueOf(id)+"\n"+  name+ "\n\n ");
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

        mQueue.add(request);
    }
}
