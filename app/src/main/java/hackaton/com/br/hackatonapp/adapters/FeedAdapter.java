package hackaton.com.br.hackatonapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.ui.core.products.Product;

import java.util.List;

/**
 * Created by gustefr on 23/03/2016.
 */
public class FeedAdapter extends BaseAdapter {

    private final Context context;
    private final List<Product> productList;

    public FeedAdapter(Context context, List<Product> productList) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return getProductList().size();
    }

    @Override
    public Object getItem(int position) {
        return getProductList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.feed_component, null);
        }

        if (getProductList().size() > 0) {
            ((TextView) v.findViewById(R.id.textViewFeedName)).setText(getProductList().get(position).getName());
            ((TextView) v.findViewById(R.id.textViewFeedPrice)).setText(getProductList().get(position).getPrice());
            ((TextView) v.findViewById(R.id.textViewFeedQuantity)).setText(String.valueOf(getProductList().get(position).getTotalOrdered()));
            ((TextView) v.findViewById(R.id.textViewFeedTotalPrice)).setText(String.valueOf(getProductList().get(position).getTotalPrice()));
        }
        else
        {
            ((TextView) v.findViewById(R.id.textViewFeedName)).setText("Carrinho vazio");
        }
        return v;
    }

    public List<Product> getProductList() {
        return productList;
    }
}

