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

    ArrayList<TrelloCard> arrayList = new ArrayList<>();
    TextView text2 ;

    public RecycleAdapter(ArrayList<TrelloCard> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.to_do_list, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText(arrayList.get(i).getId());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView textView;

        public MyViewHolder( View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.toDoText);

        }

    }
}


