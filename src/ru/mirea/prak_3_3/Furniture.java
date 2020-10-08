package ru.mirea.prak_3_3;

abstract class Furniture{
    private String material;
    private int price;
    private String name;
    private String color;
    private String dimensions; //габариты

    Furniture(String material, String color, String dimensions, int price, String name){
        this.material = material;
        this.color = color;
        this.dimensions = dimensions; //габариты
        this.price = price;
        this.name = name;
    }

    public abstract String displayInfo();

    public String getColor(){
        return color;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMaterial(){
        return material;
    }

    public String getDimensions(){
        return dimensions;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setMaterial(String material){
        this.material = material;
    }

    public void setDimensions(String dimensions){
        this.dimensions = dimensions;
    }
}

class Chair extends Furniture{ //стул
    private int backHeight; //высота спинки
    private int seatHeight; //высота сидения

    Chair(String material, String color, String dimensions, int price, String name){
        super(material, color, dimensions, price, name);
    }

    public void setBackHeight(int backHeight) {
        this.backHeight = backHeight;
    }

    public void setSeatHeight(int seatHeight) {
        this.seatHeight = seatHeight;
    }

    public int getSeatHeight(){
        return seatHeight;
    }

    public int getBackHeight(){
        return backHeight;
    }

    @Override
    public String displayInfo(){
        return ("Материал: " + getMaterial() + "; Цвет: " + getColor() + "; Габариты: " + getDimensions() +
                "; Высота спинки: " + getBackHeight() + "; Высота сидения: " + getSeatHeight());
    }
}

class Wardrobe extends Furniture { //шкаф
    private double height;
    private double width;

    Wardrobe(String material, String color, String dimensions, int price, String name){
        super(material, color, dimensions, price, name);
    }

    @Override
    public String displayInfo(){
        return ("Материал: " + getMaterial() + "; Цвет: " + getColor() + "; Габариты: " + getDimensions() +
                "; Высота: " + getHeight() + "; Ширина: " + getWidth());
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}

class Sofa extends Furniture{ //диван
    private String form;
    private int numberOfSeats;

    Sofa(String material, String color, String dimensions, int price, String name) {
        super(material, color, dimensions, price, name);
    }

    @Override
    public String displayInfo(){
        return ("Материал: " + getMaterial() + "; Цвет: " + getColor() + "; Габариты: " + getDimensions() +
                "; Форма дивана: " + getForm() + "; Количество мест: " + getNumberOfSeats());
    }

    public String getForm() {
        return form;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
