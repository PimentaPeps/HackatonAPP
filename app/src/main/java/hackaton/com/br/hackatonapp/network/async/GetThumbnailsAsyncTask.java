package hackaton.com.br.hackatonapp.network.async;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by gustefr on 22/03/2016.
 */
public class GetThumbnailsAsyncTask extends AsyncTask<Void, Void, String> {
    private final Context context;

    public GetThumbnailsAsyncTask(Context context){
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
        // Instantiate the RequestQueue.
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
