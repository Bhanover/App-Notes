package com.example.front_notes.models;

import org.json.JSONException;
import org.json.JSONObject;

public class GitData {

    private String name;
    private String description;
    private String url;

    public GitData(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }
    public GitData(JSONObject jsonObject) throws JSONException {
        this.name = jsonObject.getString("name");
        this.description = jsonObject.getString("description");
        this.url = jsonObject.getString("url");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
