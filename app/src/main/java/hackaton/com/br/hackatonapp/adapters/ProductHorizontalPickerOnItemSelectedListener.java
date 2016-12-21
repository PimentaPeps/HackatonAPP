package hackaton.com.br.hackatonapp.adapters;


import hackaton.com.br.hackatonapp.ui.HorizontalPicker;
import hackaton.com.br.hackatonapp.ui.core.products.Product;

public class ProductHorizontalPickerOnItemSelectedListener implements HorizontalPicker.OnItemSelected {

    private Product product;

    public ProductHorizontalPickerOnItemSelectedListener(Product product){
        this.product = product;
    }

    @Override
    public void onItemSelected(int index) {
        product.setKartNumber(index);
    }
}
