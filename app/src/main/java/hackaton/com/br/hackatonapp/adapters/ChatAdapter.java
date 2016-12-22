package hackaton.com.br.hackatonapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.model.ChatMessage;

/**
 * Created by gustefr on 23/03/2016.
 */
public class ChatAdapter extends BaseAdapter {

    private final Context context;
    private final List<ChatMessage> messageList;

    public ChatAdapter(Context context, List<ChatMessage> messageList) {
        this.messageList = messageList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return getMessageList().size();
    }

    @Override
    public Object getItem(int position) {
        return getMessageList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater vi = LayoutInflater.from(context);
        if (getMessageList().size() > 0) {
            if (getMessageList().get(position).getType() == ChatMessage.Type.received) {
                v = vi.inflate(R.layout.chat_component_atendente, null);
            }
            if(getMessageList().get(position).getType() == ChatMessage.Type.send) {
                v = vi.inflate(R.layout.chat_component_voce, null);
            }
        }

        if (getMessageList().size() > 0) {
            ((TextView) v.findViewById(R.id.textViewChat)).setText(getMessageList().get(position).getMessage());
        }
        else
        {
            ((TextView) v.findViewById(R.id.textViewFeedName)).setText("Sem mensagens");
        }
        return v;
    }

    public List<ChatMessage> getMessageList() {
        return messageList;
    }
}

