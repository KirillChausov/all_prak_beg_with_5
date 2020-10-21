package ru.mirea.prak_7_1;

public class Loader {
    public static void main(String[] args) {
        final String x = "Пример цвета";
        sColor(x, CEnum.COLOR_MAGENTA);
        sColor(x, CEnum.COLOR_CYAN);
        sColor(x, CEnum.COLOR_RED);
        System.out.println("\n" + "Standard color");
    }

    public static void sColor(String x, CEnum color) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_COLOR = "\u001B[" + color.getNum() + "m";

        System.out.println(ANSI_COLOR + x + ANSI_RESET);
    }
}
