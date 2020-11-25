package ru.mirea.prak_17and18;

import java.util.Scanner;

public class Loader {
    public static void main(String[] args) {
        Action act_1 = new Action("create_project");
        Action act_2 = new Action("add_library");
        Action act_3 = new Action("restart");
        Action act_4 = new Action("test");
        Action act_5 = new Action("deploy");
        Action act_6 = new Action("drop_bd");

        Action[] actions = {act_1, act_2, act_3, act_4, act_5, act_6};

        act_1.setNextActions(act_2, act_5,1, 2);
        act_2.setNextActions(act_3, act_4, 4, 6);
        act_3.setNextActions(act_4, act_5, 6, 2);
        act_4.setNextActions(act_3, act_5, 3, 5);
        act_5.setNextActions(act_1, act_3, 5, 3);

        Scanner scanner = new Scanner(System.in);
        Action head = act_1;
        while (true) {
            String tryInput = scanner.nextLine();
            try {
                int input = Integer.parseInt(tryInput);
                if (input == -1) {
                    break;
                }
                else if (input == 0 || input == 1) {
                    if (input == 0){
                        System.out.println(actions[head.getNumNameAct0() - 1].getNameAction());
                        head = head.getNextAction0();
                    }
                    else {
                        System.out.println(actions[head.getNumNameAct1() - 1].getNameAction());
                        head = head.getNextAction1();
                    }
                } else {
                    System.out.println("1, 0 или -1!!!");
                }
            } catch (Exception ex) {
                System.out.println("Введите целое число.");
            }
        }
    }
}
