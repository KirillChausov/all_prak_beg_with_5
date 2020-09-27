package ru.mirea.prak_6_1;
import java.util.Scanner;
import java.util.ArrayList;

public class Loader {
    public int numbProduct;
    public int amountOfMoney;

    public static void showCommand(){
        System.out.println("Показать каталог - 1" + "\n" +  "Купить - 2" + "\n" + "Уйти - 3");
    }

    public static void main(String[] args) {
        Chair chair_1 = new Chair("Skin", "Black", "Big", 10000, "Victoria Ghost_1");

        Sofa sofa_1 = new Sofa("Skin", "Red", "Average", 40000, "Fisher_1");

        Wardrobe wardrobe_1 = new Wardrobe("Wood", "Black", "Big", 60000, "Trio_1");

        ArrayList<Furniture> catalog = new ArrayList<Furniture>();
        catalog.add(new Chair("Skin", "Gray", "Small", 5000, "Victoria Ghost_2"));
        catalog.add(new Sofa("Velour", "Black", "Big", 100000, "Fisher_2"));
        catalog.add(new Wardrobe("Wood", "White", "Average", 150000, "Trio_2"));
        catalog.add(chair_1);
        catalog.add(sofa_1);
        catalog.add(wardrobe_1);

        System.out.print("Введите сумму Ваших денег: ");
        Scanner in = new Scanner(System.in);
        int amountOfMoney = in.nextInt();

        Loader.showCommand();

        while(true){
            int command = in.nextInt();
            if(command == 1){ //вывод каталога
                FurnitureShop.printCatalog(catalog);
            }
            else if(command == 2){
                System.out.print("Введите номер товара который хотите купить: ");
                int numbProduct = in.nextInt()-1;
                FurnitureShop.buy(catalog, numbProduct, amountOfMoney);
                Loader.showCommand();
            }
            else if(command == 3){
                System.out.println("Выход");
                break;
            }
            else{
                System.out.print("Вы неверно ввели команду. Список команд: ");
                Loader.showCommand();
            }
        }
    }
}