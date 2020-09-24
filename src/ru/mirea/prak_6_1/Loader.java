package ru.mirea.prak_6_1;
import java.util.HashMap;

public class Loader {
    public static void main(String[] args) {
        Chair chair_0 = new Chair("Skin", "Black", "Big");

        Sofa sofa_0 = new Sofa("Skin", "Red", "Average");

        Wardrobe wardrobe_0 = new Wardrobe("Wood", "Black", "Big");

        HashMap<Furniture, Integer> catalog = new HashMap<Furniture, Integer>();
        catalog.put(chair_0, 3000);
        catalog.put(new Chair("Wood","Blue", "Small"), 4000);
        catalog.put(new Chair("Metal", "Red", "Average"), 5000);
        catalog.put(sofa_0, 100000);
        catalog.put(new Sofa("Skin", "Green", "Big"), 60000);
        catalog.put(new Sofa("Microfiber", "Black", "Big"), 30000);
        catalog.put(wardrobe_0, 35000);
        catalog.put(new Wardrobe("Wood", "Black", "Big"), 45000);
        catalog.put(new Wardrobe("Wood", "Black", "Big"), 55000);

        System.out.println("Каталог: ");

       // for(int i = 0; i < catalog.size(); i++){
            System.out.println(catalog.get(chair_0));
        //}

    }
}