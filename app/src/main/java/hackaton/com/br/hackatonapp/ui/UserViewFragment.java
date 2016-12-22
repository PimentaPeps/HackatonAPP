package hackaton.com.br.hackatonapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.adapters.AlertaAdapter;
import hackaton.com.br.hackatonapp.adapters.FeedAdapter;
import hackaton.com.br.hackatonapp.adapters.ProductsAdapter;


public class UserViewFragment extends Fragment{

    //mocked data
    private String texto_alertas = "Percebi que em sua conta constam algumas alterações que merecem" +
            "a sua atenção imediata." +
            "\nA sua senha eletrônica se encontra bloqueada." +
            "\nVocê possui 1 cartão para desbloqueio." +
            "\nExistem 2 transações que precisam de confirmação, devido ao alto valor tranferido.";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_user_view, container, false);

        // ((TextView) fragmentView.findViewById(R.id.texto_aviso)).setText(texto_alertas);

        ((ListView) fragmentView.findViewById(R.id.lista_alertas)).setAdapter(new AlertaAdapter());

        return fragmentView;
    }
}