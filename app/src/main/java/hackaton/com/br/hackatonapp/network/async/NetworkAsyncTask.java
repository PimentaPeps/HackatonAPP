package hackaton.com.br.hackatonapp.network.async;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;

import hackaton.com.br.hackatonapp.Client;
import hackaton.com.br.hackatonapp.controller.Global;
import hackaton.com.br.hackatonapp.controller.ListFeed;
import hackaton.com.br.hackatonapp.ui.MainActivity;

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
        ((Global)((MainActivity)context).getApplication()).setClient(new Client());
        try {
            ((Global)((MainActivity)context).getApplication()).getClient().conectar();
            ((Global)((MainActivity)context).getApplication()).getClient().escutar();
        } catch (IOException e) {
            e.printStackTrace();
        }
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