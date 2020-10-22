package ru.mirea.prak_15;

public class Human{
    private int age = 1;

    public int getAge(){
        return age;
    }

    static int setAge(int a) throws Exception{ //метод бросающий исключение типа Exception
        return a/0;
    }

    public void addAge(int age) throws InvalidAgeException{
        if(age < 0){
            throw new InvalidAgeException("age must be positive");
        }
        this.age += age;
    }

    public void addBigage(int bAge){
        age += bAge;
        if(bAge > 100){
            throw new MyException("Сомнительно большой возраст");
        }
    }
}
