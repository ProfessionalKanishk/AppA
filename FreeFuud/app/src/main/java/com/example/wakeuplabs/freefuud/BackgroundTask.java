package com.example.wakeuplabs.freefuud;

import android.content.Context;
import android.os.AsyncTask;

import java.net.URL;

/**
 * Created by Anjana on 6/12/2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,Void> {

    Context ctx;
    BackgroundTask(Context ctx){

        this.ctx = ctx;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {
        String update = "http://10.0.2.2/webapp/";
        String method = params[0];
        if(method.equals("update")){

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
