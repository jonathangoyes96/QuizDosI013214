package com.optic.quizdosi013214;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.optic.quizdosi013214.URL.HttpManager;
import com.optic.quizdosi013214.models.User;
import com.optic.quizdosi013214.parsers.JsonUsers;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private TextView mTextViewData;

    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTextViewData = (TextView) findViewById(R.id.textViewData);


        if(isOnline()) {
            TaskUsers taskUsers = new TaskUsers();
            taskUsers.execute("https://jsonplaceholder.typicode.com/users");

        }
        else {
            Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_SHORT).show();
        }

    }

    /*
     * METODO QUE PERMITE VALIDAR EL ESTADO DE LA RED
     */
    public boolean isOnline() {
        // OBTENIENDO EL SERVICIO DE LA CONECTIVIDAD
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // DE ConnectivityManager OBTENGO SI ESTA O NO ACTIVA LA RED
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        // SI HAY CONEXION
        if(networkInfo != null) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * METODO QUE PERMITE PROCESAR LOS DATOS
     */
    public void processData() {
        // mTextViewData.append(data + "\n");
        Toast.makeText(this, String.valueOf(userList.size()), Toast.LENGTH_SHORT).show();

        for(User user  : userList) {
            mTextViewData.append(user + "\n\n");
        }
    }


    public class TaskUsers extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            processData();
        }

        // String s CONTIENE TODOS LOS DATOS PROVENIENTES DE INTERNET
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                userList = JsonUsers.getDataJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();
            mProgressBar.setVisibility(View.GONE);
        }
    }

}
