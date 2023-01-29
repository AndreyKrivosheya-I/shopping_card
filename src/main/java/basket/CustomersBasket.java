package basket;

import shoppingCart.Devices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomersBasket {

    /* Contain info about all device into shopping cart(SC) */
    private List<Devices> cart;

    public CustomersBasket() {
        cart = new ArrayList<>();
    }

    public CustomersBasket(Devices... devices) {
        cart = new ArrayList<>(Arrays.asList(devices));
    }

    public CustomersBasket(List<Devices> cart) {
        this.cart = cart;
    }

    /* Add a new device into SC */
    public void add(Devices newDevice) {
        cart.stream()
                .filter(c -> c.equalsByTypeAndPrice(newDevice))
                .findFirst()
                .ifPresentOrElse(
                        c -> c.setAmount(c.getAmount() + newDevice.getAmount()),
                        () -> cart.add(newDevice)
                );
    }

    /* Add new devices into SC from the list */
    public void addAll(List<Devices> newDevices) {
        for (Devices d : newDevices)
            add(d);
    }

    /* Return total price device from SC */
    public double countSum() {
        /* Now this method count sum without sales. I will add it near future */
        double sum = 0.;
        for (Devices d : cart)
            sum += d.getAmount() * d.getPrice();

        return sum;
    }

    public List<Devices> getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "CustomersBasket{" +
                "cart = " + cart +
                '}';
    }
}
