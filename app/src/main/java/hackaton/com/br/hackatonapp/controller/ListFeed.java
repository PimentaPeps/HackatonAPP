package hackaton.com.br.hackatonapp.controller;

import android.content.Context;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.adapters.FrameComponentAdapter;
import hackaton.com.br.hackatonapp.network.core.youtuberesponse.YouTubeResponse;
import hackaton.com.br.hackatonapp.ui.core.FrameComponent;
import hackaton.com.br.hackatonapp.volley.Request;
import hackaton.com.br.hackatonapp.volley.RequestQueue;
import hackaton.com.br.hackatonapp.volley.Response;
import hackaton.com.br.hackatonapp.volley.StringRequest;
import hackaton.com.br.hackatonapp.volley.Volley;
import hackaton.com.br.hackatonapp.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;


/**
 * Created by gustefr on 17/03/2016.
 */
public class ListFeed {
    YouTubeResponse youTubeResponse;

    public void find(final Context context) {
        //final TextView mTextView = (TextView) findViewById(R.id.text);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        //String de canais
        //String url ="https://www.googleapis.com/youtube/v3/channels?part=snippet&forUsername=klauskkpm&key=AIzaSyAEbzSTf5mmMqgwFC6LMV6Ga0bPLBFKcIg";
        //String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCFOZ9I_FwRE1hTEX65NFunA&order=viewCount&q=" + ((EditText) ((Activity) context).findViewById(R.id.contentMainTextBox)).getText().toString().replace(" ", "+") + "&type=video&videoDefinition=high&key=AIzaSyAEbzSTf5mmMqgwFC6LMV6Ga0bPLBFKcIg";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        youTubeResponse = gson.fromJson(response, YouTubeResponse.class);

                        ArrayList<FrameComponent> frameComponentArrayList = new ArrayList<>();
                        for (int i = 0; i < youTubeResponse.getItems().size(); i++) {
                            FrameComponent frameComponent = new FrameComponent();
                            frameComponent.setVideoName(youTubeResponse.getItems().get(i).getSnippet().getTitle());
                            frameComponent.setUrlVideo(youTubeResponse.getItems().get(i).getSnippet().getThumbnails().getMedium().getUrl());
                            frameComponentArrayList.add(frameComponent);
                        }

                        FrameComponentAdapter frameComponentAdapter = new FrameComponentAdapter(context, frameComponentArrayList);
                        //ListView listViewContentMain = (ListView) ((Activity) context).findViewById(R.id.listViewContentMain);
                        //listViewContentMain.setAdapter(frameComponentAdapter);

                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.toString();
                //mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
