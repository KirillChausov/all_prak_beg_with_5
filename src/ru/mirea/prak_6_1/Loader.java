package ru.mirea.prak_6_1;
import java.util.HashMap;
import java.util.ArrayList;

public class Loader {
    public static void main(String[] args) {
        Chair chair_0 = new Chair("Skin", "Black", "Big");

        Sofa sofa_0 = new Sofa("Skin", "Red", "Average");

        Wardrobe wardrobe_0 = new Wardrobe("Wood", "Black", "Big");

        ArrayList<Furniture> listOfObjects = new ArrayList<>();
        listOfObjects.add(chair_0);
        listOfObjects.add(new Chair("Metal", "Red", "Average");
        listOfObjects.add(sofa_0);
        listOfObjects.add(new Sofa("Microfiber", "Black", "Big"));
        listOfObjects.add(new Wardrobe("Wood", "Black", "Big"));

        HashMap<ArrayList, Integer> catalog = new HashMap<ArrayList, Integer>();
        catalog.put(chair_0, 10000);

    }
}