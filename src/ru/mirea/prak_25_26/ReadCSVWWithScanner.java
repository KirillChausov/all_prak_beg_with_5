package ru.mirea.prak_25_26;

import ru.mirea.prak_17and18.Action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadCSVWWithScanner {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("oscar_age.csv"));
        String line = null;
        Scanner scanner = null;
        int index = 0;
        int flag = 1;
        int maxAge = 1;
        List<Actor> actList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            if(flag == 1){
                flag += 1;
                continue;
            }
            else {
                Actor actor = new Actor();
                scanner = new Scanner(line);
                scanner.useDelimiter(", ");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (index == 0)
                        actor.setIndex(Integer.parseInt(data));
                    else if (index == 1) {
                        actor.setYear(Integer.parseInt(data));
                    } else if (index == 2) {
                        actor.setAge(Integer.parseInt(data));
                        if(maxAge < actor.getAge()){
                            maxAge = actor.getAge();
                        }
                    } else if (index == 3) {
                        actor.setName(data);
                    } else if (index == 4) {
                        scanner.useDelimiter(", ");
                        actor.setMovie(data);
                    } else {
                        System.out.println("Некорректные данные:" + data);
                    }
                    ++index;
                }
                index = 0;
                actList.add(actor);
            }
        }
        reader.close();

        for(int i = maxAge; i != 0; i--){
            int counterOsc = 0;
            String actors = "";
            for(Actor item : actList){
                if(i == item.getAge()){
                    counterOsc++;
                    if (counterOsc != 1){
                        actors += ", ";
                    }
                    actors += item.getName();
                }
            }
            if(counterOsc != 0) {
                System.out.println(i + " - " + counterOsc + " | " + actors + ".");
            }
        }
    }
}
