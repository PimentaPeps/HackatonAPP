package hackaton.com.br.hackatonapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.dao.ImageDAO;
import hackaton.com.br.hackatonapp.ui.HorizontalPicker;
import hackaton.com.br.hackatonapp.ui.MainActivity;
import hackaton.com.br.hackatonapp.ui.core.products.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustefr on 23/03/2016.
 */
public class ProductsAdapter extends BaseAdapter {

    private final Context context;
    private final List<Product> productList;
    private List<HorizontalPicker> pickerList;

    public ProductsAdapter(Context context, List<Product> productList) {
        this.productList = productList;
        this.context = context;
        pickerList = new ArrayList<>();
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
            v = vi.inflate(R.layout.product_component, null);
        }
        ((TextView) v.findViewById(R.id.textViewProductName)).setText(getProductList().get(position).getName());
        ((TextView) v.findViewById(R.id.textViewProductPrice)).setText(getProductList().get(position).getPrice());

        HorizontalPicker picker;
        if (pickerList.size() <= position) {
            picker = (HorizontalPicker) v.findViewById(R.id.horizontalPickerProduct);
            picker.setOnItemSelectedListener(new ProductHorizontalPickerOnItemSelectedListener(getProductList().get(position)));
            if (!pickerList.contains(picker))
                pickerList.add(picker);
        }

        ImageView mImageView;
        String url = getProductList().get(position).getImage();
        mImageView = (ImageView) v.findViewById(R.id.imageViewProductImage);

        //Alterar pra controller chamando DAO
        ImageDAO imageDAO = new ImageDAO(context);
        imageDAO.getImageURLAsync(mImageView, url, ((MainActivity) context).local);

        return v;
    }

    public void updatePickers() {
        for (int i = 0; i < pickerList.size(); i++) {
            pickerList.get(i).setSelectedItem(0);
        }
    }

    public List<Product> getProductList() {
        return productList;
    }
}

