package hackaton.com.br.hackatonapp.controller;

import android.support.multidex.MultiDexApplication;


import java.util.ArrayList;
import java.util.List;

import hackaton.com.br.hackatonapp.ui.core.products.Product;

/**
 * Created by gustefr on 15/04/2016.
 */
public class Global extends MultiDexApplication {

    public Global(){
        super();
        orderedProductsGlobal = new ArrayList<>();
    }

    private List<Product> orderedProductsGlobal;

    public List<Product> getOrderedProductsGlobal() {
        return orderedProductsGlobal;
    }
}
