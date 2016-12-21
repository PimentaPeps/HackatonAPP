package hackaton.com.br.hackatonapp.ui.core.products;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

/**
 * Created by gustefr on 23/03/2016.
 */
public class Product {
    private String guid;
    private String name;
    private String price;
    private String image;
    private transient int totalOrdered;
    private transient float totalPrice;
    private transient int kartNumber;


    public String getGUID() {
        return guid;
    }

    public void setGUID(String GUID) {
        this.guid = GUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        totalPrice = totalOrdered * Float.valueOf(price);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTotalOrdered() {
        return totalOrdered;
    }

    public void setTotalOrdered(int totalOrdered) {
        try {
            this.totalOrdered = totalOrdered;
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            DecimalFormat format = new DecimalFormat("0.#");
            format.setDecimalFormatSymbols(symbols);
            totalPrice = totalOrdered * format.parse(price).floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getKartNumber() {
        return kartNumber;
    }

    public void setKartNumber(int kartNumber) {
        this.kartNumber = kartNumber;
    }

    public void updateTotalOrdered() {
        try {
            totalOrdered += kartNumber;
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            DecimalFormat format = new DecimalFormat("0.#");
            format.setDecimalFormatSymbols(symbols);
            totalPrice = totalOrdered * format.parse(price).floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product)) {
            return false; //objects cant be equal
        }
        Product anotherProduct = (Product) obj;
        return this.guid.equals(anotherProduct.getGUID());

    }
}
