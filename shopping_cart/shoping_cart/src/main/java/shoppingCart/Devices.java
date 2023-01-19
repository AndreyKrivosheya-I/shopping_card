package shoppingCart;

public class Devices {
    /*Мне кажется, что лучше тип сделать неизменяем полем, без сеттера и final*/
    /* Ещё нужно сожздать метод для уменьшения стоимости по входному параметру */
    private Double price;
    private Integer amount = 1;
    private TypeOfDevices typeOfDevices;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    /* Return true if type and price devices equals, else false */
    public boolean equalsByTypeAndPrice(Devices other){
        return typeOfDevices == other.getTypeOfDevices() && price.equals(other.getPrice());
    }

    public Devices(TypeOfDevices typeOfDevices, Double price, Integer amount) {
        this.typeOfDevices = typeOfDevices;
        this.price = price;
        this.amount = amount;
    }
}

