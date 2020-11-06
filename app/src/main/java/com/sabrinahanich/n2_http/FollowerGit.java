package com.sabrinahanich.n2_http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FollowerGit {
    public List<UserGit> followers;

    public static FollowerGit getFromApi(String url) {
        String json = ConexaoApi.getJsonFromApi(url);
        try {
            JSONArray jsonArray = new JSONArray(json);
            FollowerGit followers = new FollowerGit();
            followers.followers = new ArrayList<UserGit>();
            for(int i = 0; i < jsonArray.length(); i++) {
                followers.followers.add(UserGit.getUserFromJson(jsonArray.getString(i), true));
            }
            return followers;
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
