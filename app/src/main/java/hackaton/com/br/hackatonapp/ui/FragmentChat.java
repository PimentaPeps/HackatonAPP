package hackaton.com.br.hackatonapp.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.adapters.ChatAdapter;
import hackaton.com.br.hackatonapp.controller.Global;
import hackaton.com.br.hackatonapp.interfaces.MessageInterface;
import hackaton.com.br.hackatonapp.model.ChatMessage;

/**
 * Created by gustefr on 23/03/2016.
 */
public class FragmentChat extends Fragment implements MessageInterface {

    String client_name;
    View fragmentView;
    ListView listViewChat;
    Global global;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        global = (Global) getActivity().getApplication();
        global.getClient().setMessageInterface(this);
        fragmentView = inflater.inflate(R.layout.fragment_chat, container, false);
        listViewChat = (ListView) fragmentView.findViewById(R.id.listViewChat);
        listViewChat.setAdapter(new ChatAdapter( this.getActivity(), ((Global)this.getActivity().getApplication()).getChatMessageList()));
        //final ListView fragmentFeedGridView = (ListView) fragmentView.findViewById(R.id.fragmentFeedListView);
        //fragmentFeedGridView.setAdapter(new FeedAdapter(getActivity(), global.getOrderedProductsGlobal()));
        final Button buttonBot = (Button) fragmentView.findViewById(R.id.buttonBot);
        buttonBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(((TextView)fragmentView.findViewById(R.id.botText)).getText().toString());
            }
        });
        return fragmentView;
    }

    // initiates the chat with the bot
    public void sendMessage(String message) {
        try {
            ((Global)this.getActivity().getApplication()).getClient().enviarMensagem(message);
            ((ChatAdapter) listViewChat.getAdapter()).getMessageList().add(new ChatMessage(ChatMessage.Type.send, message));
            ((ChatAdapter) listViewChat.getAdapter()).notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message inputMessage) {
            // Gets the image task from the incoming Message object.
            ((ChatAdapter)listViewChat.getAdapter()).getMessageList().add(new ChatMessage(ChatMessage.Type.received, (String)inputMessage.obj));
            ((ChatAdapter) listViewChat.getAdapter()).notifyDataSetChanged();
        }
    };

    @Override
    public void receiveMessage(String string) {
        Message completeMessage =
                mHandler.obtainMessage(1, string);
        completeMessage.sendToTarget();
    }
}
