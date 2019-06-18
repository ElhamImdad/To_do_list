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

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        textCardView = findViewById(R.id.toDoText);
        mQueue = Volley.newRequestQueue(this);
         // call json method
        jsonParse();

        adapter = new RecycleAdapter(trelloListCard);
        recyclerView.setAdapter(adapter);

    }

    private void jsonParse(){
        final String url = "https://trello.com/1/boards/ZqN99gGN/lists?cards=open&card_fields=name&filter=open&fields=name";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("array");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject result1 = jsonArray.getJSONObject(i);

                                String type = result1.getString("id");
                                String typeName = result1.getString("name");
                                JSONArray cards = result1.getJSONArray("cards");
                                String cardId = cards.getString(Integer.parseInt("id"));
                                String cardName = cards.getString(Integer.parseInt("name"));


                                TrelloCard t = new TrelloCard();
                                t.setFullName(typeName);
                                t.settitle(cardName);
                                trelloListCard.add(t);
                               /*   Log.d("Result" , id);*/
                               String cardInfo = typeName+" :\n"+cardName;
                               Log.d("Result",cardInfo);
                               // textCardView.append(String.valueOf(id)+ "\n\n ");
                                textCardView.setText(cardInfo);
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
