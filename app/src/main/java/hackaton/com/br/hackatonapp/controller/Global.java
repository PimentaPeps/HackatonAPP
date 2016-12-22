package hackaton.com.br.hackatonapp.controller;

import android.support.multidex.MultiDexApplication;

import java.util.ArrayList;
import java.util.List;

import hackaton.com.br.hackatonapp.Client;
import hackaton.com.br.hackatonapp.model.ChatMessage;
import hackaton.com.br.hackatonapp.ui.core.products.Product;

/**
 * Created by gustefr on 15/04/2016.
 */
public class Global extends MultiDexApplication {

    private Client client;
    private List<ChatMessage> chatMessageList;
    private List<Product> orderedProductsGlobal;

    public Global(){
        super();
        orderedProductsGlobal = new ArrayList<>();
        chatMessageList = new ArrayList<>();
    }

    public List<Product> getOrderedProductsGlobal() {
        return orderedProductsGlobal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }
}
