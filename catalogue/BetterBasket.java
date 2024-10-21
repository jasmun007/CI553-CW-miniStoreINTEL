package catalogue;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;

/**
 * Write a description of class BetterBasket here.
 *
 * @author Your Name
 * @version 1.0
 */
public class BetterBasket extends Basket implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean add(Product pr) {
        // Check if the product already exists in the basket
        for (Product existingProduct : this) {
            if (existingProduct.getProductNum().equals(pr.getProductNum())) {
                // If the product exists, update the quantity
                existingProduct.setQuantity(existingProduct.getQuantity() + 1); // Increment quantity by 1
                return true;
            }
        }

        // If the product does not exist, add it to the basket with quantity 1
        pr.setQuantity(1); // Set initial quantity to 1
        super.add(pr);

        // Sort the basket by product number
        Collections.sort(this, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getProductNum().compareTo(p2.getProductNum());
            }
        });

        return true;
    }

    @Override
    public String getDetails() {
        StringBuilder sb = new StringBuilder(256);
        Formatter fr = new Formatter(sb);
        String csign = (String) getCurrencyFormat();
        double total = 0.00;

        if (theOrderNum != 0) {
            fr.format("Order number: %03d%n", theOrderNum);
        }

        if (this.size() > 0) {
            for (Product pr : this) {
                int number = pr.getQuantity();
                fr.format("%-7s", pr.getProductNum());
                fr.format("%-14.14s", pr.getDescription());
                fr.format("(%3d)", number); // Quantity in brackets
                fr.format("%s%7.2f%n", csign, pr.getPrice() * number);
                total += pr.getPrice() * number;
            }
        }

        fr.format("Total%n");
        fr.format("%s%7.2f%n", csign, total);
        fr.close();

        return sb.toString();
    }

	private Object getCurrencyFormat() {
		// TODO Auto-generated method stub
		return null;
	}
}