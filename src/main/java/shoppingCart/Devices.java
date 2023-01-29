package shoppingCart;

public class Devices {
    private final Double price;
    private Integer amount = 1;
    private final TypeOfDevices typeOfDevices;

    public Devices(TypeOfDevices typeOfDevices, Double price, Integer amount) {
        this.typeOfDevices = typeOfDevices;
        this.price = price;
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public TypeOfDevices getTypeOfDevices() {
        return typeOfDevices;
    }

    /* Return true if type and price devices equals, else false */
    public boolean equalsByTypeAndPrice(Devices other) {
        return typeOfDevices == other.getTypeOfDevices() && price.equals(other.getPrice());
    }
}

