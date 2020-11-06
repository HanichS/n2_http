package com.sabrinahanich.n2_http;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class FollowersActivity extends AppCompatActivity {
    private TextView f1;
    private TextView f2;
    private TextView f3;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        f1 = (TextView)findViewById(R.id.f1);
        f2 = (TextView)findViewById(R.id.f2);
        f3 = (TextView)findViewById(R.id.f3);
        FollowersActivity.DownloadSeguidores download = new FollowersActivity.DownloadSeguidores();
        download.execute();
    }

    private class DownloadSeguidores extends AsyncTask<Void, Void, FollowerGit> {

        @Override
        protected void onPreExecute() {
            //inicia o dialog
            load = ProgressDialog.show(FollowersActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected FollowerGit doInBackground(Void... params) {
            return FollowerGit.getFromApi("https://api.github.com/users/giselezrossi/followers");
        }

        @Override
        protected void onPostExecute(FollowerGit followers) {
            f1.setText("Login: " + followers.followers.get(0).getLogin());
            f2.setText("Login: " + followers.followers.get(1).getLogin());
            f3.setText("Login: " + followers.followers.get(2).getLogin());
            load.dismiss();
        }
    }
}