package ru.mirea.prak_13;

public enum CEnum {
    COLOR_MAGENTA(35),
    COLOR_CYAN(36),
    COLOR_RED(31);
    private final int number;


    CEnum(int number) {
        this.number = number;
    }

    public int getNum() {
        return number;
    }
}
