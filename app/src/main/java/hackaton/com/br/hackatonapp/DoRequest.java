package hackaton.com.br.hackatonapp;

/** Copyright 2014 Dr Richard Wallace */

import android.os.AsyncTask;

import hackaton.com.br.hackatonapp.ui.FragmentFeed;

public class DoRequest extends AsyncTask<String, Void, String> {
    private static final String TAG = "PandorabotsTalkAPIDemo";
    public static String botname = "hackaton";
    private final FragmentFeed main;
    PandorabotsAPI pApi;
    
    public DoRequest(FragmentFeed main, String clientName) {
        super();
        this.main = main;
        this.pApi = new PandorabotsAPI(MagicParameters.hostname, MagicParameters.username, MagicParameters.userkey, clientName);
    }
   
    @Override
    protected String doInBackground(String... strings) {
        return pApi.talk(botname, strings[0]);
    }
    @Override
    protected void onPostExecute(String result) {
    	main.processBotResponse(result);
    }
}
