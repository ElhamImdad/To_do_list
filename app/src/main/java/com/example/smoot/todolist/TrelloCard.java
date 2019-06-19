package com.example.smoot.todolist;

import java.util.ArrayList;

public class TrelloCard {
    String fullName;
    String id;
    String due;
    String title;
    ArrayList<String> cardList = new ArrayList<>();

    public ArrayList<String> getCardList() {
        return cardList;
    }

    public void setCardList(ArrayList<String> cardList) {
        this.cardList = cardList;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String text) {
        this.title = text;
    }
}

