package hackaton.com.br.hackatonapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hackaton.com.br.hackatonapp.R;

/**
 * Created by gustefr on 23/03/2016.
 */
public class FragmentProducts extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_products, container, false);

        return fragmentView;
    }
}
