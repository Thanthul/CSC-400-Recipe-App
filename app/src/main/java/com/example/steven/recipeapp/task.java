package com.example.steven.recipeapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

public class task extends AsyncTask<String, Integer, Integer> {

    public interface TaskFinishedListener {
        void onTaskFinished();
    }


    private final ProgressBar progressBar;

    private final TaskFinishedListener finishedListener;


    public task(ProgressBar progressBar, TaskFinishedListener finishedListener) {
        this.progressBar = progressBar;
        this.finishedListener = finishedListener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        Log.i("", " "+params[0]);
        if(resourcesDontAlreadyExist()){
            downloadResources();
        }

        return 1;
    }

    private boolean resourcesDontAlreadyExist() {

        return true;
    }


    private void downloadResources() {

        int count = 10;
        for (int i = 0; i < count; i++) {


            int progress = (int) ((i / (float) count) * 100);
            publishProgress(progress);


            try { Thread.sleep(1000); } catch (InterruptedException ignore) {}
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        finishedListener.onTaskFinished();
    }
}
