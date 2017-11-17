package com.example.arturmusayelyan.threadmultithreadindhandler;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView tv_json;
    private ProgressBar progressBar;

    private String JSON_STRING;
    private String valueFromRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_json = (TextView) findViewById(R.id.json_textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.GONE);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getJson_button:
                new BackgroundTask().execute();
                break;
            case R.id.parseJson_button:
                if (valueFromRequest == null) {
                    Toast.makeText(this, "first getJson", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, DisplayRecyclerView.class);
                    intent.putExtra("json_data", valueFromRequest);
                    startActivity(intent);
                    //tv_json.setText("");
                }
                break;
        }
    }

    class BackgroundTask extends AsyncTask<Void, Integer, String> {

        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "https://freemegalist.com/api.php/?action=categories";
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }



        protected void onProgressUpdate(Integer... values) {
          //  System.out.println("List"+"worked "+values);

        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.GONE);
            tv_json.setText(result);

            valueFromRequest = result;
        }
    }
}
