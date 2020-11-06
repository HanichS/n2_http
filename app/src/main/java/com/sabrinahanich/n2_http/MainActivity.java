package com.sabrinahanich.n2_http;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView login;
    private TextView name;
    private TextView id;
    private TextView url;
    private TextView bio;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button followers = findViewById(R.id.button);
        login = (TextView)findViewById(R.id.login);
        name = (TextView)findViewById(R.id.name);
        bio = (TextView)findViewById(R.id.bio);
        url = (TextView)findViewById(R.id.url);
        DownloadPessoa download = new DownloadPessoa();
        download.execute();

        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFollowers = new Intent (MainActivity.this, FollowersActivity.class);
                startActivityForResult(intentFollowers, 0);
            }
        });

    }

    private class DownloadPessoa extends AsyncTask<Void, Void, UserGit> {

        @Override
        protected void onPreExecute() {
            //inicia o dialog
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected UserGit doInBackground(Void... params) {
            return UserGit.getUserFromApi("https://api.github.com/users/giselezrossi");
        }

        @Override
        protected void onPostExecute(UserGit pessoa) {
            login.setText("Login: " + pessoa.getLogin());
            name.setText("Nome: " + pessoa.getName());
            bio.setText("Bio: " + pessoa.getBio()) ;
            url.setText("Url: " + pessoa.getUrl() );
            load.dismiss();
        }
    }
}