package hackaton.com.br.hackatonapp.dao;

import android.content.Context;
import android.content.res.Resources;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.ui.MainActivity;
import hackaton.com.br.hackatonapp.ui.core.products.Product;
import hackaton.com.br.hackatonapp.ui.core.products.ProductList;
import hackaton.com.br.hackatonapp.volley.Request;
import hackaton.com.br.hackatonapp.volley.RequestQueue;
import hackaton.com.br.hackatonapp.volley.Response;
import hackaton.com.br.hackatonapp.volley.StringRequest;
import hackaton.com.br.hackatonapp.volley.Volley;
import hackaton.com.br.hackatonapp.volley.VolleyError;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by gustefr on 13/04/2016.
 */
public class ProductsDAO {
    RequestQueue queue;
    Context context;

    public ProductsDAO(Context context) {
        this.context = context;
    }

    public List<Product> getProducts(String url) {
        queue = Volley.newRequestQueue(context);
        final ProductList productList = new ProductList();
        if (((MainActivity) context).local) {
            //Deserialize Products into a streamer object
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(R.raw.products);
            try {
                byte[] b = new byte[in_s.available()];
                in_s.read(b);
                Gson gson = new Gson();
                productList.setProductList(gson.fromJson(new String(b), ProductList.class).getProductList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            productList.setProductList(gson.fromJson(response, ProductList.class).getProductList());

                            // Display the first 500 characters of the response string.
                            //mTextView.setText("Response is: "+ response.substring(0,500));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.toString();
                    //mTextView.setText("That didn't work!");
                }
            });
            queue.add(stringRequest);
        }

        return productList.getProductList();
    }

    public void addProduct(Product product) {
//// TODO: 13/04/2016
    }
}
