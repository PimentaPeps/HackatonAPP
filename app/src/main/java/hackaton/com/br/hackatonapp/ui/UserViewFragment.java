package hackaton.com.br.hackatonapp.ui;


import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.adapters.AlertaAdapter;
import hackaton.com.br.hackatonapp.simulacao.ChequeDevolvidoActivity;
import hackaton.com.br.hackatonapp.simulacao.TransferenciaActivity;
import hackaton.com.br.hackatonapp.simulacao.TrocarSenhaActivity;
import hackaton.com.br.hackatonapp.ui.core.alerta.Alerta;


public class UserViewFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView lv;
    private JSONArray json_alertas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_user_view, container, false);
        List<Alerta> lista_alertas = new ArrayList<Alerta>();

        JSONObject temp = null;

        try {
            temp = new JSONObject(getRawJSON());
            json_alertas = temp.getJSONArray("notificacao");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        lista_alertas = retorna_alertas(json_alertas);

        lv = ((ListView) fragmentView.findViewById(R.id.lista_alertas));
        lv.setAdapter(new AlertaAdapter(getContext(), lista_alertas));
        lv.setOnItemClickListener(this);

        return fragmentView;
    }

    private List<Alerta> retorna_alertas(JSONArray json_alertas) {
        List<Alerta> lista_alertas = new ArrayList<Alerta>();
        try {
            for (int i = 0; i < json_alertas.length(); i++) {
                JSONObject jobj = json_alertas.getJSONObject(i);
                Alerta a = new Alerta();
                a.setMensagem(jobj.getString("mensagem"));
                a.setDirecionamento(jobj.getString("action"));
                a.setPrioridade(jobj.getInt("prioridade"));
                a.setTipo(jobj.getString("tipo"));
                System.out.println(a);
                lista_alertas.add(a);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista_alertas;
    }

    @NonNull
    private String getRawJSON() {
        InputStream is = getResources().openRawResource(R.raw.alertas);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return writer.toString();
    }

    @Override
    public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {

        Intent intent = null;

        if (((TextView) view.findViewById(R.id.alerta_titulo)).getText().toString().contains("senha"))
            intent = new Intent(getContext(), TrocarSenhaActivity.class);

        if (((TextView) view.findViewById(R.id.alerta_titulo)).getText().toString().contains("transferência"))
            intent = new Intent(getContext(), TransferenciaActivity.class);

        if (((TextView) view.findViewById(R.id.alerta_titulo)).getText().toString().contains("cheque"))
            intent = new Intent(getContext(), ChequeDevolvidoActivity.class);

        final Intent finalIntent = intent;

        new AlertDialog.Builder(getContext())
                .setTitle("Encaminhando..")
                .setMessage(((TextView) view.findViewById(R.id.alerta_titulo)).getText().toString())
                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (finalIntent != null) {
                            getActivity().finish();
                            startActivity(finalIntent);
                        }
                    }
                })
                .setNegativeButton("não", new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    public void onClick(DialogInterface dialog, int which) {
                        json_alertas.remove(i);
                        lv.setAdapter(new AlertaAdapter(getContext(), retorna_alertas(json_alertas)));
                    }
                })
                .setIcon(R.drawable.alert_red)
                .show();
    }
}