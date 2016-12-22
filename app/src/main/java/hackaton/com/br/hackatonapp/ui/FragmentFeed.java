package hackaton.com.br.hackatonapp.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hackaton.com.br.hackatonapp.DoRequest;
import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.adapters.ChatAdapter;
import hackaton.com.br.hackatonapp.adapters.FeedAdapter;
import hackaton.com.br.hackatonapp.controller.Global;
import hackaton.com.br.hackatonapp.model.ChatMessage;

/**
 * Created by gustefr on 23/03/2016.
 */
public class FragmentFeed extends Fragment {

    String client_name;
    View fragmentView;
    ListView listViewAssistente;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_feed, container, false);
        Global global = (Global) getActivity().getApplication();
        Bundle bundle = this.getArguments();
        client_name = bundle.getString("client_name");
        listViewAssistente = (ListView) fragmentView.findViewById(R.id.listViewAssistente);
        listViewAssistente.setAdapter(new ChatAdapter( this.getActivity(), ((Global)this.getActivity().getApplication()).getAssistenteMessageList()));
        final Button buttonBot = (Button) fragmentView.findViewById(R.id.buttonAssistente);
        buttonBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askHumanMessage(((TextView)fragmentView.findViewById(R.id.editTextAssistente)).getText().toString());
            }
        });
        return fragmentView;
    }

    // initiates the chat with the bot
    public void askHumanMessage(String message) {
        DoRequest botChat = new DoRequest(this, client_name);
        botChat.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, message);
        ((ChatAdapter) listViewAssistente.getAdapter()).getMessageList().add(new ChatMessage(ChatMessage.Type.send, message));
        ((ChatAdapter) listViewAssistente.getAdapter()).notifyDataSetChanged();
    }

    /// process the bots response to id any non natural language response text
    public void processBotResponse(String result) {
        result = removeTags(result);
        showBotResponse(result);
    }

    /// display the bots response in the text view
    public void showBotResponse(String message) {
        ((ChatAdapter)listViewAssistente.getAdapter()).getMessageList().add(new ChatMessage(ChatMessage.Type.received, message));
        ((ChatAdapter) listViewAssistente.getAdapter()).notifyDataSetChanged();
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
