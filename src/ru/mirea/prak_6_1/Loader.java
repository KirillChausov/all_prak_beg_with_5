package ru.mirea.prak_6_1;
import java.util.Scanner;
import java.util.ArrayList;

public class Loader {
    public int numbProduct;
    public int amountOfMoney;

    public static void showCommand(){
        System.out.print("Показать каталог - 1" + "\n" +  "Добавить в корзину - 2" + "\n" + "Убрать из корзины - 3" + "\n" +
                "Показать корзину - 4" + "\n" + "Купить товары из корзины - 5" + "\n" + "Показать список команд - 6" + "\n" + "Уйти - 7"
                + "\n" + "Ваша команда: ");
    }

    public static void main(String[] args) {
        ArrayList<Furniture> basket = new ArrayList<Furniture>();

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
            else if(command == 2){ //добавить в корзину
                System.out.print("Введите номер товара который хотите добавить в корзину: ");
                int numbProduct = in.nextInt()-1;
                FurnitureShop.getBas(catalog, basket, numbProduct);
            }
            else if(command == 3){ //убрать из корзины
                System.out.println("Введите номер товара который хотите убрать из корзины: ");
                int numbProduct = in.nextInt()-1;
                FurnitureShop.delBas(basket, numbProduct);
            }
            else if(command == 4){
                FurnitureShop.showBasket(basket);
            }
            else if(command == 5){ //покупка
                System.out.println("Стоимость всех товаров из Вашей корзины составляет: " + FurnitureShop.getSumBasket());
                FurnitureShop.buy(amountOfMoney, basket);
            }
            else if(command == 6){ //вывод списка команд
                Loader.showCommand();
            }
            else if(command == 7){ //выход
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