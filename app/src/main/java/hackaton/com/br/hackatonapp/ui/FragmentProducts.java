package hackaton.com.br.hackatonapp.ui;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.adapters.ProductsAdapter;
import hackaton.com.br.hackatonapp.controller.Global;
import hackaton.com.br.hackatonapp.controller.ProductsController;
import hackaton.com.br.hackatonapp.ui.core.products.Product;

import java.util.List;

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
