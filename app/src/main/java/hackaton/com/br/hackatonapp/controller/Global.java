package hackaton.com.br.hackatonapp.controller;

import android.support.multidex.MultiDexApplication;
import android.support.v4.util.ArrayMap;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hackaton.com.br.hackatonapp.Client;
import hackaton.com.br.hackatonapp.model.ChatMessage;
import hackaton.com.br.hackatonapp.ui.core.products.Product;

/**
 * Created by gustefr on 15/04/2016.
 */
public class Global extends MultiDexApplication {

    public Global(){
        super();
        orderedProductsGlobal = new ArrayList<>();
        chatMessageList = new ArrayList<>();
        assistenteMessageList = new ArrayList<>();
    }
    private Client client;

    private List<ChatMessage> chatMessageList;
    private List<ChatMessage> assistenteMessageList;

    private List<Product> orderedProductsGlobal;

    public List<Product> getOrderedProductsGlobal() {
        return orderedProductsGlobal;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    public List<ChatMessage> getAssistenteMessageList() {
        return assistenteMessageList;
    }

    public void setAssistenteMessageList(List<ChatMessage> assistenteMessageList) {
        this.assistenteMessageList = assistenteMessageList;
    }
}
