package clients.cashier;

import catalogue.Basket;
import catalogue.BetterBasket;
import catalogue.Product;
import middle.MiddleFactory;
import middle.OrderProcessing;

/**
 * The Cashier Controller
 */

public class CashierController {
    private CashierModel model = null;
    private CashierView view = null;
    private Basket basket = new BetterBasket();

    /**
     * Constructor
     *
     * @param model The model
     * @param view  The view from which the interaction came
     */
    public CashierController(CashierModel model, CashierView view) {
        this.view = view;
        this.model = model;
    }

    /**
     * Check interaction from view
     *
     * @param pn The product number to be checked
     */
    public void doCheck(String pn) {
        model.doCheck(pn);
    }

    /**
     * Buy interaction from view
     */
    public void doBuy() {
        Product prod = model.doBuy();
        if (prod != null)
            basket.add(prod);
        view.updateBasket(basket);
    }

    /**
     * Bought interaction from view
     */
    public void doBought() {
        OrderProcessing op = MiddleFactory.makeOrderProcessing();
        int orderNum = op.newOrder(basket);
        view.updateBasket(orderNum);
        basket = new BetterBasket();
        view.updateBasket(basket);
    }
}