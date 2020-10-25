package ru.mirea.prak_5_1;

public class Loader {
    public static void main(String[] args) {
        Cup cup_1 = new Cup("Ceramics", "Black", "Russia");
        cup_1.setV(0.33);
        System.out.println(cup_1.displayInfo());
        Fork Fork_1 = new Fork("Iron", "metallic", "China");
        Fork_1.setLength(12.5);
        System.out.println(Fork_1.displayInfo());
        Plate Plate_1 = new Plate("Ceramics", "White", "Italy");
        Plate_1.setDiam(15);
        System.out.println(Plate_1.displayInfo());
    }
}
