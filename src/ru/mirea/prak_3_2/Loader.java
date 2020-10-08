package ru.mirea.prak_3_2;

public class Loader {
    public static void main(String[] args) {
        Bulldog bulldog_1 = new Bulldog(5, "Black", "Average");
        bulldog_1.setNeckSize("Отсутствует");
        System.out.println(bulldog_1.displayInfo());
        Chihuahua chihuahua_1 = new Chihuahua(0, "Red", "Small");
        chihuahua_1.setWeigth(8.9);
        System.out.println("\n" + chihuahua_1.displayInfo());
        Labrador labrador_1 = new Labrador(20, "Gray", "Big");
        labrador_1.setAmountOfWool("Много");
        System.out.println("\n" + labrador_1.displayInfo());
    }
}
