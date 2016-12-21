package hackaton.com.br.hackatonapp.ui.core.feed;


import hackaton.com.br.hackatonapp.ui.core.products.Product;

/**
 * Created by gustefr on 23/03/2016.
 */
public class ConsumedItem {
    private String GUID;
    private Product product;


    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public Product getName() {
        return product;
    }

    public void setName(Product name) {
        this.product = name;
    }
}
