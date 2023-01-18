package shoppingCart;

public class Devices {
    private  Integer price;
    private Integer amount = 1;
    private  TypeOfDevices typeOfDevices;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public void setTypeOfDevices(TypeOfDevices typeOfDevices) {
        this.typeOfDevices = typeOfDevices;
    }

    public Devices(TypeOfDevices typeOfDevices, Integer price, Integer amount) {
        this.typeOfDevices = typeOfDevices;
        this.price = price;
        this.amount = amount;
    }
}

