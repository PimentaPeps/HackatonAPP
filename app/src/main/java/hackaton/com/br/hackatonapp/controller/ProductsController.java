package hackaton.com.br.hackatonapp.controller;

import android.content.Context;

import java.util.List;

import hackaton.com.br.hackatonapp.dao.ProductsDAO;
import hackaton.com.br.hackatonapp.ui.core.products.Product;

/**
 * Created by gustefr on 23/03/2016.
 */
public class ProductsController {

    Context context;

    public ProductsController(Context context){
        this.context = context;
    }

    public List<Product> getProducts(String url) {
        //find fragmentProductsGridView and attach it to a new adapter
        return new ProductsDAO(context).getProducts(url);
    }

    public void addProduct(Product product){
        new ProductsDAO(context).addProduct(product);
    }
}
