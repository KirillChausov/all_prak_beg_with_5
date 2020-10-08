package ru.mirea.prak_3_3;
import java.util.ArrayList;

public class FurnitureShop {
    private String storeName;
    private String buyersName;

    FurnitureShop(String storeName, String byersName){
        this.storeName = storeName;
        this.buyersName = byersName;
    }

    static void printCatalog(ArrayList<Furniture> catalog){
        for(int i = 0; i < catalog.size(); i++){
            System.out.println(i+1 + ") " + catalog.get(i).getName() + " " + catalog.get(i).getPrice());
        }
    }

    static void buy(ArrayList<Furniture> catalog, int numbProduct, int amountOfMoney){
        if(amountOfMoney >= catalog.get(numbProduct).getPrice()){
            amountOfMoney -= catalog.get(numbProduct).getPrice();
            catalog.remove(numbProduct);
            System.out.println("Вы успешно купили выбранный товар!!!" + " Остаток средств: " + amountOfMoney);
        }
        else {
            System.out.println("Недостаточно средст ");
        }
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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
                '}';
    }
}
