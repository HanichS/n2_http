package com.sabrinahanich.n2_http;

import org.json.JSONException;
import org.json.JSONObject;

public class UserGit {
    private String id;
    private String login;
    private String url;
    private String name;
    private String bio;

    public static UserGit getUserFromApi(String url) {
        String json = ConexaoApi.getJsonFromApi(url);
        return getUserFromJson(json, false);
    }

    public static UserGit getUserFromJson(String json, Boolean follower) {
        try {
            UserGit pessoa = new UserGit();
            JSONObject jsonObj = new JSONObject(json);
            pessoa.setLogin(jsonObj.getString("login"));
            pessoa.setId(jsonObj.getString("id"));
            pessoa.setUrl(jsonObj.getString("url"));
            if(!follower) {
                pessoa.setName(jsonObj.getString("name"));
                pessoa.setBio(jsonObj.getString("bio"));
            }
            return pessoa;

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
