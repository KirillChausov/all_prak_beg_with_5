package ru.mirea.prak_3_1;

abstract class Dash {
    private String material;
    private String color;
    private String creatorCountry;

    Dash(String material, String color, String creatorCountry){
        this.material = material;
        this.color = color;
        this.creatorCountry = creatorCountry;
    }
    public abstract String displayInfo();

    public String getColor(){
        return color;
    }

    public String getCreatorCountry(){
        return creatorCountry;
    }

    public String getMaterial(){
        return material;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCreatorCountry(String creatorCountry) {
        this.creatorCountry = creatorCountry;
    }

    public void setMaterial(String material){
        this.material = material;
    }
}

class Cup extends Dash{
    public double V;

    Cup(String material, String color, String creatorCountry){
        super(material, color, creatorCountry);
    }

    @Override
    public String displayInfo(){
       return ("Материал:" + getMaterial() + "; Цвет:" + getColor() + "; Страна производителя:" + getCreatorCountry() + "; Объём чашки:" + getV());
    }

    public void setV(double V){
        this.V = V;
    }

    public double getV() {
        return V;
    }
}

class Plate extends Dash{
    public int diam;

    Plate(String material, String color, String creatorCountry){
        super(material, color, creatorCountry);
    }

    @Override
    public String displayInfo(){
        return ("\n" + "Материал:" + getMaterial() + "; Цвет:" + getColor() + "; Страна производителя:" + getCreatorCountry() + "; Диаметр тарелки" + getDiam());
    }

    public void setDiam(int diam){
        this.diam = diam;
    }

    public double getDiam(){
        return diam;
    }
}

class Fork extends Dash{
    public double length;

    Fork(String material, String color, String creatorCountry){
        super(material, color, creatorCountry);
    }

    @Override
    public String displayInfo(){
        return ("\n" + "Материал:" + getMaterial() + "; Цвет:" + getColor() + "; Страна производителя:" + getCreatorCountry() + "; Длинна вилки:" + getLength());
    }

    public void setLength(double length){
        this.length = length;
    }

    public double getLength(){
        return length;
    }
}