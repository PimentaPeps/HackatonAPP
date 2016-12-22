package hackaton.com.br.hackatonapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.ui.core.alerta.Alerta;

/**
 * Created by hipolito on 21/12/2016.
 */

public class AlertaAdapter extends BaseAdapter {

    private Context context;
    private List<Alerta> alertas;

    public AlertaAdapter(Context context, List<Alerta> alertas) {
        this.context = context;
        this.alertas = alertas;
    }

    @Override
    public int getCount() {
        return getAlertas().size();
    }

    @Override
    public Object getItem(int i) {
        return getAlertas().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.alerta_component, null);
        }
        ((TextView) v.findViewById(R.id.alerta_titulo)).setText(getAlertas().get(i).getMensagem());
        ((TextView) v.findViewById(R.id.alerta_canal_resolucao)).setText(getAlertas().get(i).getTipo());

        ImageView imv = (ImageView) v.findViewById(R.id.alerta_imagem_notificacao);

        if (getAlertas().get(i).getPrioridade() == 1) {
            imv.setImageResource(R.drawable.alert_blue);
        }
        if (getAlertas().get(i).getPrioridade() == 2) {
            imv.setImageResource(R.drawable.alert_yellow);
        }
        if (getAlertas().get(i).getPrioridade() == 3) {
            imv.setImageResource(R.drawable.alert_red);
        }

        return v;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }

}
