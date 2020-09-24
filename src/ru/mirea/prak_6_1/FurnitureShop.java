package ru.mirea.prak_6_1;

public class FurnitureShop {
    private String storeName;
    private String buyersName;
    private double amountOfMoney;

    FurnitureShop(String storeName, String byersName, double amountOfMoney){
        this.storeName = storeName;
        this.buyersName = byersName;
        this.amountOfMoney = amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public String getBuyersName() {
        return buyersName;
    }

    public String getStoreName() {
        return storeName;
    }

    @Override
    public String toString() {
        return "FurnitureShop{" +
                "storeName='" + storeName + '\'' +
                ", buyersName='" + buyersName + '\'' +
                ", amountOfMoney=" + amountOfMoney +
                '}';
    }
}
