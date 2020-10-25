package ru.mirea.prak_6_1;
import java.util.ArrayList;

public class FurnitureShop {
    private String storeName;
    private String buyersName;
    static int sumBasket = 0;
    static int counterObjBas = 0;

    FurnitureShop(String storeName, String byersName){
        this.storeName = storeName;
        this.buyersName = byersName;
    }

    static void printCatalog(ArrayList<Furniture> catalog){
        System.out.println("Каталог:");
        for(int i = 0; i < catalog.size(); i++){
            System.out.println(i+1 + ") " + catalog.get(i).getName() + " " + catalog.get(i).getPrice());
        }
    }

    static void getBas(ArrayList<Furniture> catalog, ArrayList<Furniture> basket, int numbProduct){
        basket.add(catalog.get(numbProduct));
        System.out.println("Товар " + basket.get(counterObjBas).getName() + " добавлен в корзину!");
        sumBasket += basket.get(counterObjBas).getPrice();
        ++counterObjBas;
    }

    static void delBas(ArrayList<Furniture> basket, int numbProduct){
        if(!basket.isEmpty()) {
            System.out.println("Товар " + basket.get(numbProduct).getName() + " удалён из корзины!");
            sumBasket -= basket.get(numbProduct).getPrice();
            basket.remove(numbProduct);
            --counterObjBas;
        }
        else{
            System.out.println("Корзина пуста!");;
        }
    }

    static void showBasket(ArrayList<Furniture> basket){
        System.out.println("Корзина: ");
        if(basket.isEmpty()){
            System.out.println("Пуста");;
        }
        else {
            for (int i = 0; i < basket.size(); i++) {
                System.out.println(i + 1 + ")" + basket.get(i).getName() + " " + basket.get(i).getPrice());
            }
        }
    }

    static int getSumBasket(){
        return sumBasket;
    }

    static void buy(int amountOfMoney, ArrayList<Furniture> basket){
        if (amountOfMoney >= sumBasket) {
            amountOfMoney -= sumBasket;
            basket.clear();
            System.out.println("Вы успешно купили товар!" + " Остаток средств: " + amountOfMoney);
        }
        else{
            System.out.println("Недостаточно средств");
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
