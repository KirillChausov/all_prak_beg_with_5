package ru.mirea.prak_3_2;

abstract class Dog {
    private int tailLength;
    private String color;
    private String size;

    Dog(int tailLength, String color, String size){
        this.tailLength = tailLength;
        this.color = color;
        this.size = size;
    }
    public abstract String displayInfo();

    public String getColor(){
        return color;
    }

    public String getSize(){
        return size;
    }

    public int getTailLength(){
        return tailLength;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setTailLength(int tailLength){
        this.tailLength = tailLength;
    }
}

class Chihuahua extends Dog {
    public double weigth;

    Chihuahua(int tailLength, String color, String size){
        super(tailLength, color, size);
    }

    @Override
    public String displayInfo(){
        return ("Длинна хвоста:" + getTailLength() + "; Цвет:" + getColor() + "; Размер:" + getSize() + "; Средний вес:" + getWeigth());
    }

    public void setWeigth(double weigth){
        this.weigth = weigth;
    }

    public double getWeigth() {
        return weigth;
    }
}

class Bulldog extends Dog{
    public String neckSize;

    Bulldog(int tailLength, String color, String size){
        super(tailLength, color, size);
    }

    @Override
    public String displayInfo(){
        return ("Длинна хвоста:" + getTailLength() + "; Цвет:" + getColor() + "; Размер:" + getSize() + "; Размер шеи:" + getNeckSize());
    }

    public void setNeckSize(String neckSize){
        this.neckSize = neckSize;
    }

    public String getNeckSize() {
        return neckSize;
    }
}

class Labrador extends Dog{
    public String amountOfWool;

    Labrador(int tailLength, String color, String size){
        super(tailLength, color, size);
    }

    @Override
    public String displayInfo(){
        return ("Длинна хвоста:" + getTailLength() + "; Цвет:" + getColor() + "; Размер:" + getSize() + "; Количество шерсти:" + getAmountOfWool());
    }

    public String getAmountOfWool() {
        return amountOfWool;
    }

    public void setAmountOfWool(String amountOfWool){
        this.amountOfWool = amountOfWool;
    }
}