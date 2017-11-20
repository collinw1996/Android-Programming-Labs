package edu.towson.cosc431.collinwoodruff;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Collin on 11/20/2017.
 */

public class FakeWork {

    interface OnFinishedWork{
        void onDone(String result);
        void onProgress(Integer progress);
    }

    public FakeWork(){

    }

    public void start(final OnFinishedWork listener){

        AsyncTask<Void, Integer, String> task = new AsyncTask<Void, Integer, String>() {

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                listener.onDone(result);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                listener.onProgress(values[0] * 10);
            }

            @Override
            protected String doInBackground(Void... voids) {
                for(int i = 1; i < 11; i++){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(i);
                }
                return "Hello World";
            }
        };

        task.execute();
    }
}
