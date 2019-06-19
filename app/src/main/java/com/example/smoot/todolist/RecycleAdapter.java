package com.example.smoot.todolist;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>{


    ArrayList<String> cards;

    public RecycleAdapter(ArrayList<String> cards) {

        this.cards=cards;
    }
    public  class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView textView1;

        public MyViewHolder( View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.toDoText);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.to_do_list, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MyViewHolder myViewHolder, int i) {
        //+arrayList.get(i).gettitle()
       // TrelloCard t = arrayList.get(i);
       // t.setCardList(cards);
        //myViewHolder.textView1.setText(cards.get(i));
        Log.e("inside adapter", cards.get(i));
        myViewHolder.textView1.setText(cards.get(i));
       // myViewHolder.textView1.append(t.cardList.get(i));


    }



    @Override
    public int getItemCount() {
        return cards.size();
    }
}


