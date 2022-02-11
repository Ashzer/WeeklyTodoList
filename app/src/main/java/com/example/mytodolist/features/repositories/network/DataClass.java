package com.example.mytodolist.features.repositories.network;

import com.google.gson.annotations.SerializedName;

public class DataClass {
    @SerializedName("title")
    public String title;

    @SerializedName("body")
    public String body;

    @Override
    public String toString() {
        return "PostResult \n{\ntitle = " + title + "\n, body = " + body + "\n}";
    }
}
