package hackaton.com.br.hackatonapp.network.async;

import android.content.Context;
import android.os.AsyncTask;

import hackaton.com.br.hackatonapp.controller.ListFeed;

/**
 * Created by gustefr on 21/03/2016.
 */
public class NetworkAsyncTask extends AsyncTask<Void, Void, String> {

    private final Context context;

    public NetworkAsyncTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        /**
         * show dialog
         */
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        (new ListFeed()).find(context);
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        /**
         * update ui thread and remove dialog
         */
        super.onPostExecute(result);
    }
}