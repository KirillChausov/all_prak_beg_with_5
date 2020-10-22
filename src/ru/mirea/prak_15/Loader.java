package ru.mirea.prak_15;

public class Loader {
    public static void main(String[] args) {
        int a = 5;
        int bAge = 110;
        Human human = new Human();
        try{
            int x = Human.setAge(a);
            human.addAge(10);
            human.addBigage(bAge);
        }

        catch (InvalidAgeException e){
            System.out.println("Incorrect age");
            System.out.println(e.getMessage());
        /*} catch (MyException e){
            System.out.println("Incorrect age_2");
            System.out.println(e.getMessage());*/
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Итоговый возраст: " + human.getAge());
        }
    }
}
