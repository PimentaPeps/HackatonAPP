package hackaton.com.br.hackatonapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hackaton.com.br.hackatonapp.DoRequest;
import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.controller.Global;

/**
 * Created by gustefr on 23/03/2016.
 */
public class FragmentFeed extends Fragment {

    String client_name;
    View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_feed, container, false);
        //final ListView fragmentFeedGridView = (ListView) fragmentView.findViewById(R.id.fragmentFeedListView);
        Global global = (Global) getActivity().getApplication();
        //fragmentFeedGridView.setAdapter(new FeedAdapter(getActivity(), global.getOrderedProductsGlobal()));
        Bundle bundle = this.getArguments();
        client_name = bundle.getString("client_name");
        final Button buttonBot = (Button) fragmentView.findViewById(R.id.buttonBot);
        buttonBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askHumanMessage(((TextView)fragmentView.findViewById(R.id.botText)).getText().toString());
            }
        });
        return fragmentView;
    }

    // initiates the chat with the bot
    public void askHumanMessage(String message) {
        DoRequest botChat = new DoRequest(this, client_name);
        botChat.execute(message);

    }

    /// process the bots response to id any non natural language response text
    public void processBotResponse(String result) {
        result = removeTags(result);
        showBotResponse(result);
    }

    /// display the bots response in the text view
    public void showBotResponse(String message) {
        TextView textView = (TextView) fragmentView.findViewById(R.id.botResponse);
        textView.setText(message);
    }

    private String removeTags(String string) {
        Pattern REMOVE_TAGS = Pattern.compile("<.+?>");

        if (string == null || string.length() == 0) {
            return string;
        }
        Matcher m = REMOVE_TAGS.matcher(string);
        return m.replaceAll("");
    }
}
