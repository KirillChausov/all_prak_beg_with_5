####CalculatorPanel.java
```java
package ru.mirea.prak_11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorPanel {
    CalculatorPanel() {
        JFrame calculator = new JFrame("Calculator");
        calculator.setLayout(new FlowLayout());
        calculator.setSize(280, 200);
        calculator.setDefaultCloseOperation(calculator.EXIT_ON_CLOSE);
        calculator.setLocationRelativeTo(null);

        JTextField textField_1 = new JTextField(10);
        calculator.add(textField_1);

        JTextField textField_2 = new JTextField(10);
        calculator.add(textField_2);

        JButton addition = new JButton("+");
        calculator.add(addition);

        JButton subtraction = new JButton("-");
        calculator.add(subtraction);

        JButton division = new JButton("/");
        calculator.add(division);

        JButton multiplication = new JButton("*");
        calculator.add(multiplication);

        JButton idwr = new JButton("%");
        calculator.add(idwr);

        JLabel result = new JLabel("Ответ: ");
        calculator.add(result);


        addition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double n_1 = 0;
                double n_2 = 0;
                try {
                    n_1 = Double.parseDouble(textField_1.getText());
                    n_2 = Double.parseDouble(textField_2.getText());
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(calculator, "Некорректный ввод!");
                }
                result.setText(String.valueOf(n_1 + " + " + n_2 +" = " + ((double)n_1 + n_2)));
            }
        });

        subtraction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double n_1 = 0;
                double n_2 = 0;
                try {
                    n_1 = Double.parseDouble(textField_1.getText());
                    n_2 = Double.parseDouble(textField_2.getText());
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(calculator, "Некорректный ввод!");
                }
                result.setText(String.valueOf(n_1 + " - " + n_2 +" = " + ((double)n_1 - n_2)));
            }
        });
        calculator.setVisible(true);

        division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double n_1 = 0;
                double n_2 = 0;
                try {
                    n_1 = Double.parseDouble(textField_1.getText());
                    n_2 = Double.parseDouble(textField_2.getText());
                    if(n_2 == 0){
                        throw new Exception("Делить на ноль нельзя!");
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(calculator, "Некорректный ввод!");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                if(n_2 != 0) {
                    result.setText(String.valueOf(n_1 + " / " + n_2 + " = " + ((double) n_1 / n_2)));
                }
            }
        });

        multiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double n_1 = 0;
                double n_2 = 0;
                try {
                    n_1 = Double.parseDouble(textField_1.getText());
                    n_2 = Double.parseDouble(textField_2.getText());
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(calculator, "Некорректный ввод!");
                }
                result.setText(String.valueOf(n_1 + " * " + n_2 +" = " + ((double)n_1 * n_2)));
            }
        });

        idwr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n_1 = 0;
                int n_2 = 0;
                try {
                    n_1 = Integer.parseInt(textField_1.getText());
                    n_2 = Integer.parseInt(textField_2.getText());
                    if(n_2 == 0){
                        throw new Exception("Целочисленное деление на ноль!");
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(calculator, "Некорректный ввод!");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                if(n_2 != 0) {
                    result.setText(String.valueOf(n_1 + " % " + n_2 + " = " + ((int) n_1 % n_2)));
                }
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculatorPanel();
            }
        });
    }
}
```
####AGamer.java
```java
package ru.mirea.prak_12;

public abstract class AGamer {
    protected String sign;

    abstract boolean shot(int x, int y);
    abstract boolean win();
}
```
####AI.java
```java
package ru.mirea.prak_12;

import java.util.Random;

public class AI extends AGamer {
    MainGameField gameField;
    String playerSign = "";
    static int aiLevel = 0;

    public AI(String sign, int aiLevel, String playerSign) {
        this.sign = sign;
        this.playerSign = playerSign;
        this.aiLevel = aiLevel;
    }

    boolean shot(int x, int y) {
        gameField = MainGameField.getInstance();
        x = -1;
        y = -1;
        boolean ai_win = false;
        boolean user_win = false;
        // Находим выигрышный ход
        if (aiLevel == 2) {
            for (int i = 0; i < gameField.linesCount; i++) {
                for (int j = 0; j < gameField.linesCount; j++) {
                    if (!gameField.isCellBusy(i, j)) {
                        gameField.cell[i][j] = sign;
                        if (gameField.checkWin(sign)) {
                            x = i;
                            y = j;
                            ai_win = true;
                        }
                        gameField.cell[i][j] = gameField.NOT_SIGN;
                    }
                }
            }
        }
        // Блокировка хода пользователя, если он побеждает на следующем ходу
        if (aiLevel > 0) {
            if (!ai_win) {
                for (int i = 0; i < gameField.linesCount; i++) {
                    for (int j = 0; j < gameField.linesCount; j++) {
                        if (!gameField.isCellBusy(i, j)) {
                            gameField.cell[i][j] = this.playerSign;
                            if (gameField.checkWin(this.playerSign)) {
                                x = i;
                                y = j;
                                user_win = true;
                            }
                            gameField.cell[i][j] = gameField.NOT_SIGN;
                        }
                    }
                }
            }
        }
        if (!ai_win && !user_win) {
            do {
                Random rnd = new Random();
                x = rnd.nextInt(gameField.linesCount);
                y = rnd.nextInt(gameField.linesCount);
            }
            while (gameField.isCellBusy(x, y));
        }
        gameField.cell[x][y] = sign;
        return true;
    }

    boolean win() {
        gameField = MainGameField.getInstance();
        return gameField.checkWin(this.sign);
    }
}
```
####MainClass.java
```java
package ru.mirea.prak_12;

public class MainClass {
    public static void main(String[] args) {
        MainForm gameForm = new MainForm();
    }
}
```
####MainForm.java
```java
package ru.mirea.prak_12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    public MainForm() {
        setTitle("XO game GUI");
        setBounds(300, 300, 455, 525);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainGameField gameField = MainGameField.getInstance();
        // Создаём панель для кнопок
        JPanel buttonPanel = new JPanel(new GridLayout());
        add(gameField, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        JButton btnStart = new JButton("Начать новую игру");
        JButton btnEnd = new JButton("Закончить игру");
        buttonPanel.add(btnStart);
        buttonPanel.add(btnEnd);
        setVisible(true);

        btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(btnStart.getText());
                MainGameField gameField = MainGameField.getInstance();
                if (btnStart.getText().isEmpty()) {
                    gameField.linesCount = 3;
                }
                else {
                    try {
                        gameField.linesCount = Integer.parseInt(btnStart.getText());
                    }
                    catch (NumberFormatException ex) {
                        System.out.println("Необходимо ввести целое число!");
                    }
                }
                gameField.startNewGame();
                gameField.gameMode = 2;
                gameField.aiLevel = 0;
            }
        });
    }
}
```
####MainGameField.java
```java
package ru.mirea.prak_12;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGameField extends JPanel {
    private static MainGameField instance = null;
    public static final int FIELD_SIZE = 450;
    public final String NOT_SIGN = "*";
    boolean gameOver = false;
    String gameOverMessage = "";
    static int linesCount = 3;
    int cellSize;
    int x;
    int y;
    boolean nextTurn = false;
    Player player1;
    Player player2;
    int gameMode = 1;
    int aiLevel = 0;
    public String[][] cell;

    public static synchronized MainGameField getInstance() {
        if (instance == null)
            instance = new MainGameField();
        return instance;
    }

    void startNewGame() {
        gameOver = false;
        gameOverMessage = "";
        cellSize = FIELD_SIZE / linesCount;
        cell = new String[linesCount][linesCount];
        repaint();
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                cell[i][j] = NOT_SIGN;
            }
        }
        setVisible(true);
    }

    private MainGameField() {
        setVisible(false);
        player1 = new Player("X");
        player2 = new Player("O");


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                x = e.getX() / cellSize;
                y = e.getY() / cellSize;
                System.out.println("Mouse clicked on " + e.getX() + " " + e.getY());

                if (!gameOver) {
                    switch (gameMode) {
                        case 1: {
                            twoPlayersMode();
                            break;
                        }
                        case 2: {
                            modeAgainstAI();
                            break;
                        }
                    }
                }
            }
        });
    }

    void twoPlayersMode() {
        if (player1.isShotReady == 1) {
            nextTurn = true;
            player2.isShotReady = 0;
            System.out.println("Player 1 shot!");
            player1.shot(x,y);
        }
        if (player1.win()) {
            System.out.println("Player 1 WIN!!!");
            gameOver = true;
            gameOverMessage = "Player 1 WIN!!!";
        }
        repaint();
        if (isFieldFull() && !player1.win() && !player2.win()) {
            gameOver = true;
            gameOverMessage = "DRAW!!!";
        }
        if (player2.isShotReady == 1) {
            nextTurn = false;
            player1.isShotReady = 0;
            System.out.println("Player 2 shot!");
            player2.shot(x,y);
        }
        if (!gameOver) {
            player2.shot(x, y);
        }
        if (player2.win()) {
            System.out.println("Player 2 WIN!!!");
            gameOver = true;
            gameOverMessage = "Player 2 WIN!!!";
        }
        repaint();
        if (isFieldFull() && !player2.win() && !player1.win()) {
            gameOver = true;
            gameOverMessage = "DRAW!!!";
        }
        if (nextTurn) {
            player1.isShotReady = 0;
            player2.isShotReady = 1;
        }
        else {
            player1.isShotReady = 1;
            player2.isShotReady = 0;
        }
    }

    void modeAgainstAI() {
        Player player = new Player("X");
        AI ai = new AI("O", aiLevel, player.sign);
        if (!gameOver) {
            if (player.shot(x, y)) {
                if (player.win()) {
                    System.out.println("Player WIN!!!");
                    gameOver = true;
                    gameOverMessage = "Player WIN!!!";
                }
                if (isFieldFull()) {
                    gameOver = true;
                    gameOverMessage = "DRAW!!!";
                }
                repaint();
                if (!gameOver) {
                    ai.shot(x, y);
                }
                if (ai.win()) {
                    System.out.println("AI WIN!!!");
                    gameOver = true;
                    gameOverMessage = "AI WIN!!!";
                }
                repaint();
                if (isFieldFull() && !ai.win()) {
                    gameOver = true;
                    gameOverMessage = "DRAW!!!";
                }
            }
        }
    }

    boolean isCellBusy(int x, int y) {
        if (x < 0 || y < 0 || x > linesCount - 1 || y > linesCount - 1) {
            return false;
        }
        return cell[x][y] != NOT_SIGN;
    }

    public boolean isFieldFull() {
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if (cell[i][j] == NOT_SIGN)
                    return false;
            }
        }
        return true;
    }

    public boolean checkLine(int start_x, int start_y, int dx, int dy, String sign) {
        for (int i = 0; i < linesCount; i++) {
            if (cell[start_x + i * dx][start_y + i * dy] != sign)
                return false;
        }
        return true;
    }

    public boolean checkWin(String sign) {
        for (int i = 0; i < linesCount; i++) {
            // проверяем строки
            if (checkLine(i, 0, 0, 1, sign)) return true;
            // проверяем столбцы
            if (checkLine(0, i, 1, 0, sign)) return true;
        }
        // проверяем диагонали
        if (checkLine(0, 0, 1, 1, sign)) return true;
        if (checkLine(0, linesCount - 1, 1, -1, sign)) return true;
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i <= this.linesCount; i++) {
            g.drawLine(0, i * this.cellSize, FIELD_SIZE, i * this.cellSize);
            g.drawLine(i * this.cellSize, 0, i * this.cellSize, FIELD_SIZE);
        }
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if (cell[i][j] != NOT_SIGN) {
                    if (cell[i][j] == "X") {
                        g.setColor(Color.RED);
                        g.drawLine((i * cellSize), (j * cellSize), (i + 1) * cellSize, (j + 1) * cellSize);
                        g.drawLine((i + 1) * cellSize, (j * cellSize), (i * cellSize), (j + 1) * cellSize);
                    }
                    if (cell[i][j] == "O" || cell[i][j] == "O") {
                        g.setColor(Color.BLUE);
                        g.drawOval((i * cellSize), (j * cellSize), cellSize, cellSize);
                    }
                }
            }
        }

        if (gameOver) {
            g.setColor(Color.BLACK);
            g.fillRect(0, FIELD_SIZE / 2, FIELD_SIZE, FIELD_SIZE / 8);
            g.setColor(Color.RED);
            g.setFont(new Font("Tahoma", 10, 40));
            g.drawString(gameOverMessage, 0, 19 * FIELD_SIZE / 32);
        }
    }
}
```
####Player.java
```java
package ru.mirea.prak_12;

public class Player extends AGamer{
    MainGameField gameField;
    int isShotReady = 1;

    public Player(String sign) {
        this.sign = sign;
    }

    boolean shot(int x, int y) {
        gameField = MainGameField.getInstance();
        if (!gameField.isCellBusy(x, y)) {
            gameField.cell[x][y] = sign;
            return true;
        }
        return false;
    }

    boolean win() {
        gameField = MainGameField.getInstance();
        return gameField.checkWin(this.sign);
    }
}
```
####CEnum.java
```java
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
```
####Loader.java
```java
package ru.mirea.prak_13;

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
```
####Company.java
```java
package ru.mirea.prak_14;

public class Company {

}
```
####CPrinter.java
```java
package ru.mirea.prak_14;

public class CPrinter implements handleEmployees{
    @Override
    public void handleEmployees(Employee employee) {
        System.out.println("-----------");
        System.out.println(employee);
        System.out.println("-----------");
    }
}
```
####Employee.java
```java
package ru.mirea.prak_14;
import java.util.ArrayList;

public class Employee {
    private String name;
    private String surname;
    private int birthday;
    private String residence; //место жительства
    private int phoneNumber;
    private int salary;

    ArrayList<Employee> employees = new ArrayList<>();

    Employee(String name, String surname, int birthday, String residence, int phoneNumber, int salary){
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.residence = residence;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
```
####EmpSelector.java
```java
package ru.mirea.prak_14;

public interface EmpSelector {
    boolean isNeed(Employee employee);
}
```
####handleEmployees.java
```java
package ru.mirea.prak_14;

public interface handleEmployees {
    void handleEmployees(Employee employee);
}
```
####Loader.java
```java
package ru.mirea.prak_14;

import java.util.Random;

public class Loader {
    public static void main(String[] args) {
        Company company = new Company();
        Random random = new Random(70000);
    }
}
```
####YearSelector.java
```java
package ru.mirea.prak_14;

public class YearSelector implements EmpSelector{
    private int birthday;

    public YearSelector(int birthday) {
        this.birthday=birthday;
    }

    @Override
    public boolean isNeed(Employee employee) {
        return employee.getBirthday()> birthday;
    }
}
```
####Human.java
```java
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
```
####InvalidAgeException.java
```java
package ru.mirea.prak_15;

public class InvalidAgeException extends Exception{
    InvalidAgeException(String message){
        super(message);
    }
}
```
####Loader.java
```java
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
```
####MyException.java
```java
package ru.mirea.prak_15;

public class MyException extends RuntimeException {
    MyException(String message) {
        super(message);
    }
}
```
####Loader.java
```java
package ru.mirea.prak_16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class Loader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выбор решения:" + "\n" + "1.Регулярные выражения" + "\n" + "2.Без регулярных выражений");
        int numb = scanner.nextInt();

        if(numb == 1){
            int numberRules = 0;
            System.out.print("Введите количество правил: ");
            try{
                numberRules = scanner.nextInt();
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
            String path;
            String rep;
            Map<String, String> rule = new HashMap<>();
            for (int i = 0; i <= numberRules; i++) {
                Pattern pattern = Pattern.compile("(?<path>\\w+) (?<rep>\\w+)");
                Matcher matcher = pattern.matcher(scanner.nextLine());
                if (matcher.find()) {
                    path = matcher.group("path");
                    rep = matcher.group("rep");
                    rule.put(path, rep);
                }
            }

            String text = scanner.nextLine();
            List<Map.Entry<String, String>> ruleConvert = new ArrayList<>(rule.entrySet());
            StringBuilder regex = new StringBuilder();
            for (int i = 0; i < ruleConvert.size(); i++) {
                regex.append(ruleConvert.get(i).getKey());
                if (i < ruleConvert.size()-1) {
                    regex.append("|");
                }
            }
            Pattern onePattern = Pattern.compile(regex.toString());
            Matcher oneMatcher = onePattern.matcher(text);
            String result = oneMatcher.replaceAll(index -> rule.get(index.group()));
            System.out.println("result: " + result);
        }
        else if(numb == 2){
            int numberRules = 0;
            System.out.print("Введите количество правил: ");
            try {
                numberRules = scanner.nextInt();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Map<String, String> rule = new HashMap<>();
            for (int i = 0; i < numberRules; i++) {
                rule.put(scanner.next(), scanner.next());
            }
            String text = scanner.next();
            String result = text;
            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < rule.size(); j++) {
                    String t = (String) rule.keySet().toArray()[j];
                    String r = (String) rule.values().toArray()[j];
                    if (i + t.length() < result.length() && result.substring(i, i + t.length()).equals(t)) {
                        result = result.replace(t, r + " ");
                        i += r.length();
                        break;
                    }
                }
            }
            result = result.replace(" ", "");
            System.out.println(result);
        }
    }
}
```
####Action.java
```java
package ru.mirea.prak_17and18;

public class Action {
    private String nameAction;
    private int numNameAct0;
    private int numNameAct1;
    private Action nextAction0;
    private Action nextAction1;

    public Action(String nameAction) {
        this.nameAction = nameAction;
    }

    public String getNameAction() {
        return nameAction;
    }

    public Action getNextAction0() {
        return nextAction0;
    }

    public Action getNextAction1() {
        return nextAction1;
    }

    public int getNumNameAct0() {
        return numNameAct0;
    }

    public int getNumNameAct1() {
        return numNameAct1;
    }

    public void setNextActions(Action nextAction0, Action nextAction1, int numNameAct0, int numNameAct1) {
        this.nextAction0 = nextAction0;
        this.nextAction1 = nextAction1;
        this.numNameAct0 = numNameAct0;
        this.numNameAct1 = numNameAct1;
    }
}
```
####Loader.java
```java
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
```
####Loader.java
```java
package ru.mirea.prak_19_20;

public class Loader {
    public static void main(String[] args) {
        Something app = new Something();
        app.exec();
    }
}
```
####Something.java
```java
package ru.mirea.prak_19_20;
import java.io.*;
import java.nio.file.Paths;

public class Something {

    public void exec() {
        File tmp = new File("src/ru/mirea/prak_19_20/result.md");
        tmp.delete();
        File file = new File("src");
        search(file);
    }

    public void search(File file) {
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String str : list) {
                search(new File(String.valueOf(Paths.get(file.getAbsolutePath(), str).toAbsolutePath())));
            }
        } else if (file.getAbsolutePath().endsWith(".java")) {
            writeToFile(file);
        }
    }

    public void writeToFile(File file) {
        try {
            try (
                    BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                    FileWriter writer = new FileWriter("src/ru/mirea/prak_19_20/result.md", true)
            ) {
                writer.write("####" + file.getName() + "\n");
                writer.write("```java\n");
                String line = reader.readLine();
                while (line != null) {
                    writer.write(line);
                    writer.write('\n');
                    line = reader.readLine();
                }
                writer.write("```\n");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Something wrong with path");
        } catch (IOException e) {
            System.out.println("Something wrong I can feel it");
        }
    }
}
```
####ClientHandler.java
```java
package ru.mirea.prak_21_22;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// реализуем интерфейс Runnable, который позволяет работать с потоками
public class ClientHandler implements Runnable {
    // экземпляр нашего сервера
    private Server server;
    // исходящее сообщение
    private PrintWriter outMessage;
    // входящее собщение
    private Scanner inMessage;
    private static final String HOST = "localhost";
    private static final int PORT = 3443;
    // клиентский сокет
    private Socket clientSocket = null;
    // количество клиента в чате, статичное поле
    private static int clients_count = 0;

    // конструктор, который принимает клиентский сокет и сервер
    public ClientHandler(Socket socket, Server server) {
        try {
            clients_count++;
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    // Переопределяем метод run(), который вызывается когда
    // мы вызываем new Thread(client).start();
    @Override
    public void run() {
        try {
            while (true) {
                // сервер отправляет сообщение
                server.sendMessageToAllClients("Новый участник вошёл в чат!");
                server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
                break;
            }

            while (true) {
                // Если от клиента пришло сообщение
                if (inMessage.hasNext()) {
                    String clientMessage = inMessage.nextLine();
                    // если клиент отправляет данное сообщение, то цикл прерывается и
                    // клиент выходит из чата
                    if (clientMessage.equalsIgnoreCase("##session##end##")) {
                        break;
                    }
                    // выводим в консоль сообщение (для теста)
                    System.out.println(clientMessage);
                    // отправляем данное сообщение всем клиентам
                    server.sendMessageToAllClients(clientMessage);
                }
                // останавливаем выполнение потока на 100 мс
                Thread.sleep(100);
            }
        }
        catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                this.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // отправляем сообщение
    public void sendMsg(String msg) {
        try {
            outMessage.println(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // клиент выходит из чата
    public void close() throws IOException {
        // удаляем клиента из списка
        server.removeClient(this);
        clients_count--;
        server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
    }
}
```
####ClientWindow.java
```java
package ru.mirea.prak_21_22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientWindow extends JFrame {
    // адрес сервера
    private static final String SERVER_HOST = "localhost";
    // порт
    private static final int SERVER_PORT = 3443;
    // клиентский сокет
    private Socket clientSocket;
    // входящее сообщение
    private Scanner inMessage;
    // исходящее сообщение
    private PrintWriter outMessage;
    // следующие поля отвечают за элементы формы
    private JTextField jtfMessage;
    private JTextField jtfName;
    private JTextArea jtaTextAreaMessage;
    // имя клиента
    private String clientName = "";
    // получаем имя клиента
    public String getClientName() {
        return this.clientName;
    }

    // конструктор
    public ClientWindow() {
        try {
            // подключаемся к серверу
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Задаём настройки элементов на форме
        setBounds(600, 300, 600, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jtaTextAreaMessage = new JTextArea();
        jtaTextAreaMessage.setEditable(false);
        jtaTextAreaMessage.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jtaTextAreaMessage);
        add(jsp, BorderLayout.CENTER);
        // label, который будет отражать количество клиентов в чате
        JLabel jlNumberOfClients = new JLabel("Количество клиентов в чате: ");
        add(jlNumberOfClients, BorderLayout.NORTH);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbSendMessage = new JButton("Отправить");
        bottomPanel.add(jbSendMessage, BorderLayout.EAST);
        jtfMessage = new JTextField("Введите ваше сообщение: ");
        bottomPanel.add(jtfMessage, BorderLayout.CENTER);
        jtfName = new JTextField("Введите ваше имя: ");
        bottomPanel.add(jtfName, BorderLayout.WEST);
        // обработчик события нажатия кнопки отправки сообщения
        jbSendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // если имя клиента, и сообщение непустые, то отправляем сообщение
                if (!jtfMessage.getText().trim().isEmpty() && !jtfName.getText().trim().isEmpty()) {
                    clientName = jtfName.getText();
                    sendMsg();
                    // фокус на текстовое поле с сообщением
                    jtfMessage.grabFocus();
                }
            }
        });
        // при фокусе поле сообщения очищается
        jtfMessage.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfMessage.setText("");
            }
        });
        // при фокусе поле имя очищается
        jtfName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfName.setText("");
            }
        });
        // в отдельном потоке начинаем работу с сервером
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // бесконечный цикл
                    while (true) {
                        // если есть входящее сообщение
                        if (inMessage.hasNext()) {
                            // считываем его
                            String inMes = inMessage.nextLine();
                            String clientsInChat = "Клиентов в чате = ";
                            if (inMes.indexOf(clientsInChat) == 0) {
                                jlNumberOfClients.setText(inMes);
                            } else {
                                // выводим сообщение
                                jtaTextAreaMessage.append(inMes);
                                // добавляем строку перехода
                                jtaTextAreaMessage.append("\n");
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        // добавляем обработчик события закрытия окна клиентского приложения
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    // здесь проверяем, что имя клиента непустое и не равно значению по умолчанию
                    if (!clientName.isEmpty() && clientName != "Введите ваше имя: ") {
                        outMessage.println(clientName + " вышел из чата!");
                    } else {
                        outMessage.println("Участник вышел из чата, так и не представившись!");
                    }
                    // отправляем служебное сообщение, которое является признаком того, что клиент вышел из чата
                    outMessage.println("##session##end##");
                    outMessage.flush();
                    outMessage.close();
                    inMessage.close();
                    clientSocket.close();
                } catch (IOException exc) {

                }
            }
        });
        // отображаем форму
        setVisible(true);
    }

    // отправка сообщения
    public void sendMsg() {
        // формируем сообщение для отправки на сервер
        String messageStr = jtfName.getText() + ": " + jtfMessage.getText();
        // отправляем сообщение
        outMessage.println(messageStr);
        outMessage.flush();
        jtfMessage.setText("");
    }
}
```
####Loader_C.java
```java
package ru.mirea.prak_21_22;

public class Loader_C {
    public static void main(String[] args) {
        ClientWindow clientWindow = new ClientWindow();
    }
}
```
####Loader_S.java
```java
package ru.mirea.prak_21_22;

public class Loader_S {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
    }
}
```
####Server.java
```java
package ru.mirea.prak_21_22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Server {
    // порт, который будет прослушивать наш сервер
    static final int PORT = 3443;
    // список клиентов, которые будут подключаться к серверу
    private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public Server() throws IOException {
        Date date = new Date();
        File myFile = new File("D://history.txt");
        FileWriter fileWriter = new FileWriter("D://history.txt", true);
        try {
            fileWriter.write("Сервер запущен. Дата и время: " + date.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка");
        } finally {
            fileWriter.close();
        }
        // сокет клиента, это некий поток, который будет подключаться к серверу
        // по адресу и порту
        Socket clientSocket = null;
        // серверный сокет
        ServerSocket serverSocket = null;
        try {
            // создаём серверный сокет на определенном порту
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен!");
            // запускаем бесконечный цикл
            while (true) {
                // таким образом ждём подключений от сервера
                clientSocket = serverSocket.accept();
                // создаём обработчик клиента, который подключился к серверу
                // this - это наш сервер
                ClientHandler client = new ClientHandler(clientSocket, this);
                clients.add(client);
                // каждое подключение клиента обрабатываем в новом потоке
                new Thread(client).start();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                // закрываем подключение
                clientSocket.close();
                System.out.println("Сервер остановлен");
                serverSocket.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // отправляем сообщение всем клиентам
    public void sendMessageToAllClients(String msg) throws IOException {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            System.out.println(format.format(date) + " | " + msg);
            FileWriter fileWriter = new FileWriter("D://history.txt", true);
            fileWriter = new FileWriter("D://history.txt", true);
            try {
                fileWriter.write(format.format(date) + " | " + msg + "\n");
            } catch (IOException e) {
                System.out.println("Ошибка");
            } finally {
                fileWriter.close();
            }
        }

    // удаляем клиента из коллекции при выходе из чата
    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}
```
####Actor.java
```java
package ru.mirea.prak_25_26;

public class Actor {
    private int index;
    private int year;
    private int age;
    private String name;
    private String movie;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    public String getMovie() {
        return movie;
    }
    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "index=" + index +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", movie='" + movie + '\'' +
                '}' + "\n";
    }
}
```
####ReadCSVWWithScanner.java
```java
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
        System.out.println(actList);
    }
}
```
####Dash.java
```java
package ru.mirea.prak_5_1;

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
```
####Loader.java
```java
package ru.mirea.prak_5_1;

public class Loader {
    public static void main(String[] args) {
        Cup cup_1 = new Cup("Ceramics", "Black", "Russia");
        cup_1.setV(0.33);
        System.out.println(cup_1.displayInfo());
        Fork Fork_1 = new Fork("Iron", "metallic", "China");
        Fork_1.setLength(12.5);
        System.out.println(Fork_1.displayInfo());
        Plate Plate_1 = new Plate("Ceramics", "White", "Italy");
        Plate_1.setDiam(15);
        System.out.println(Plate_1.displayInfo());
    }
}
```
####Dog.java
```java
package ru.mirea.prak_5_2;

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
```
####Loader.java
```java
package ru.mirea.prak_5_2;

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
```
####Furniture.java
```java
package ru.mirea.prak_6_1;

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
```
####FurnitureShop.java
```java
package ru.mirea.prak_6_1;
import java.util.ArrayList;

public class FurnitureShop {
    private String storeName;
    private String buyersName;
    static int sumBasket = 0;
    static int counterObjBas = 0;

    FurnitureShop(String storeName, String byersName){
        this.storeName = storeName;
        this.buyersName = byersName;
    }

    static void printCatalog(ArrayList<Furniture> catalog){
        System.out.println("Каталог:");
        for(int i = 0; i < catalog.size(); i++){
            System.out.println(i+1 + ") " + catalog.get(i).getName() + " " + catalog.get(i).getPrice());
        }
    }

    static void getBas(ArrayList<Furniture> catalog, ArrayList<Furniture> basket, int numbProduct){
        basket.add(catalog.get(numbProduct));
        System.out.println("Товар " + basket.get(counterObjBas).getName() + " добавлен в корзину!");
        sumBasket += basket.get(counterObjBas).getPrice();
        ++counterObjBas;
    }

    static void delBas(ArrayList<Furniture> basket, int numbProduct){
        if(!basket.isEmpty()) {
            System.out.println("Товар " + basket.get(numbProduct).getName() + " удалён из корзины!");
            sumBasket -= basket.get(numbProduct).getPrice();
            basket.remove(numbProduct);
            --counterObjBas;
        }
        else{
            System.out.println("Корзина пуста!");;
        }
    }

    static void showBasket(ArrayList<Furniture> basket){
        System.out.println("Корзина: ");
        if(basket.isEmpty()){
            System.out.println("Пуста");;
        }
        else {
            for (int i = 0; i < basket.size(); i++) {
                System.out.println(i + 1 + ")" + basket.get(i).getName() + " " + basket.get(i).getPrice());
            }
        }
    }

    static int getSumBasket(){
        return sumBasket;
    }

    static void buy(int amountOfMoney, ArrayList<Furniture> basket){
        if (amountOfMoney >= sumBasket) {
            amountOfMoney -= sumBasket;
            basket.clear();
            System.out.println("Вы успешно купили товар!" + " Остаток средств: " + amountOfMoney);
        }
        else{
            System.out.println("Недостаточно средств");
        }
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getBuyersName() {
        return buyersName;
    }

    public String getStoreName() {
        return storeName;
    }

    @Override
    public String toString() {
        return "FurnitureShop{" +
                "storeName='" + storeName + '\'' +
                ", buyersName='" + buyersName + '\'' +
                '}';
    }
}
```
####Loader.java
```java
package ru.mirea.prak_6_1;
import java.util.Scanner;
import java.util.ArrayList;

public class Loader {
    public int numbProduct;
    public int amountOfMoney;

    public static void showCommand(){
        System.out.print("Показать каталог - 1" + "\n" +  "Добавить в корзину - 2" + "\n" + "Убрать из корзины - 3" + "\n" +
                "Показать корзину - 4" + "\n" + "Купить товары из корзины - 5" + "\n" + "Показать список команд - 6" + "\n" + "Уйти - 7"
                + "\n" + "Ваша команда: ");
    }

    public static void main(String[] args) {
        ArrayList<Furniture> basket = new ArrayList<Furniture>();

        Chair chair_1 = new Chair("Skin", "Black", "Big", 10000, "Victoria Ghost_1");

        Sofa sofa_1 = new Sofa("Skin", "Red", "Average", 40000, "Fisher_1");

        Wardrobe wardrobe_1 = new Wardrobe("Wood", "Black", "Big", 60000, "Trio_1");

        ArrayList<Furniture> catalog = new ArrayList<Furniture>();
        catalog.add(new Chair("Skin", "Gray", "Small", 5000, "Victoria Ghost_2"));
        catalog.add(new Sofa("Velour", "Black", "Big", 100000, "Fisher_2"));
        catalog.add(new Wardrobe("Wood", "White", "Average", 150000, "Trio_2"));
        catalog.add(chair_1);
        catalog.add(sofa_1);
        catalog.add(wardrobe_1);

        System.out.print("Введите сумму Ваших денег: ");
        Scanner in = new Scanner(System.in);
        int amountOfMoney = in.nextInt();

        Loader.showCommand();

        while(true){
            int command = in.nextInt();
            if(command == 1){ //вывод каталога
                FurnitureShop.printCatalog(catalog);
            }
            else if(command == 2){ //добавить в корзину
                System.out.print("Введите номер товара который хотите добавить в корзину: ");
                int numbProduct = in.nextInt()-1;
                FurnitureShop.getBas(catalog, basket, numbProduct);
            }
            else if(command == 3){ //убрать из корзины
                System.out.println("Введите номер товара который хотите убрать из корзины: ");
                int numbProduct = in.nextInt()-1;
                FurnitureShop.delBas(basket, numbProduct);
            }
            else if(command == 4){
                FurnitureShop.showBasket(basket);
            }
            else if(command == 5){ //покупка
                System.out.println("Стоимость всех товаров из Вашей корзины составляет: " + FurnitureShop.getSumBasket());
                FurnitureShop.buy(amountOfMoney, basket);
            }
            else if(command == 6){ //вывод списка команд
                Loader.showCommand();
            }
            else if(command == 7){ //выход
                System.out.println("Выход");
                break;
            }
            else{
                System.out.print("Вы неверно ввели команду. Список команд: ");
                Loader.showCommand();
            }
        }
    }
}
```
####Loader.java
```java
package ru.mirea.prak_6_2;

public class Loader {
    public static void main(String[] args) {
        Circle circle_1 = new Circle(5, "Black", true);
        circle_1.setRadius(6.5);
        circle_1.setColor("White");
        System.out.println("Радиус круга: " + circle_1.getRadius() + "; Цвет круга: " + circle_1.getColor());
        System.out.println("\n" + "Площадь круга: " + circle_1.getArea() + "; Периметр круга: " + circle_1.getPerimeter());
        System.out.println(circle_1.toString());

        Rectangle rectangle_1 = new Rectangle(7.5, 11.2);
        rectangle_1.setLength(7.5);
        rectangle_1.setWidth(11.2);
        System.out.println("Длина прямоугольника: " + rectangle_1.getLength() + "; Ширина прямоугольника: " + rectangle_1.getWidth());
        System.out.println("\n" + "Площадь прямоугольника: " + rectangle_1.getArea() + "; Периметр прямоугольника: " + rectangle_1.getPerimeter() + "\n");
        System.out.println(rectangle_1.toString());

        Square square_1 = new Square(4.6);
        System.out.println("\n" + square_1.toString());
    }
}
```
####Shape.java
```java
package ru.mirea.prak_6_2;

abstract class Shape {
    protected String color;
    protected boolean filled;

    Shape(){}

    Shape(String color, boolean filled){
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    abstract double getArea();
    abstract double getPerimeter();
   // abstract String toString();
}

class Circle extends Shape{
    protected double radius;

    Circle(){}

    Circle(double radius){
        this.radius = radius;
    }

    Circle(double radius, String color, boolean filled){
        this.radius = radius;
        this.color = color;
        this.filled = filled;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    double getPerimeter() {
        return 2*Math.PI*radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "color='" + color + '\'' +
                ", filled=" + filled +
                ", radius=" + radius +
                '}';
    }
}

class Rectangle extends Shape{
    protected double width;
    protected double length;

    Rectangle(){}

    Rectangle(double width, double length){
        this.width = width;
        this.length = length;
    }

    Rectangle(double width, double length, String color, boolean filled){
        this.width = width;
        this.length = length;
        this.color = color;
        this.filled = filled;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    @Override
    double getArea() {
        return width*length;
    }

    @Override
    double getPerimeter() {
        return 2*(width+length);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "color='" + color + '\'' +
                ", filled=" + filled +
                ", width=" + width +
                ", length=" + length +
                '}';
    }
}

class Square extends Rectangle{
    protected double side;

    Square(){}

    Square(double side){
        this.side = side;
    }

    Square(double side, String color, boolean filled){
        this.side = side;
        this.color = color;
        this.filled = filled;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
        length = side;
        width = side;
    }

    @Override
    public void setWidth(double side) {
        super.setWidth(width);
        length = this.getWidth();
        side = width;
    }

    @Override
    public void setLength(double side) {
        super.setLength(length);
        width = this.getLength();
        side = length;
    }

    @Override
    public String toString() {
        return "Square{" +
                "color='" + color + '\'' +
                ", filled=" + filled +
                ", width=" + width +
                ", length=" + length +
                ", side=" + side +
                '}';
    }
}
```
####Company.java
```java
package ru.mirea.prak_7and8;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private double income = 0;
    private ArrayList<Employee> employeeArrayList = new ArrayList<>();

    public void hire(Employee employees){
        employeeArrayList.add(employees);
    }

    public void hireAll(ArrayList<Employee> listEmployee){
        employeeArrayList.addAll(listEmployee);
    }

    public void fire(Employee employee){
        employeeArrayList.remove(employee);
    }

    public void fire(int j){
        employeeArrayList.remove(j);
        calcIncome();
    }

    public double getIncome() {
        return income;
    }

    List<Employee> getTopSalaryStaff(int count){
        ArrayList<Employee> temp = employeeArrayList;
        Employee x;
        for(int i = 0;i < temp.size() - 1; i++){
            for(int j = i + 1; j < temp.size(); j++){
                if(temp.get(i).getPosition().calcSalary(temp.get(i).getBaseSalary()) < temp.get(j).getPosition().calcSalary(temp.get(j).getBaseSalary())) {
                    x = temp.get(j);
                    temp.set(j, temp.get(i));
                    temp.set(i, x);
                }
            }
        }
        return temp.subList(0, count);
    }

    List<Employee> getLowestSalaryStaff(int count){
        ArrayList<Employee> temp = employeeArrayList;
        Employee x;
        for(int i = 0;i < temp.size() - 1; i++) {
            for (int j = i + 1; j < temp.size(); j++) {
                if(temp.get(i).getPosition().calcSalary(temp.get(i).getBaseSalary()) > temp.get(j).getPosition().calcSalary(temp.get(j).getBaseSalary())) {
                    x = temp.get(j);
                    temp.set(j, temp.get(i));
                    temp.set(i, x);
                }
            }
        }
        return temp.subList(0, count);
    }

    public void calcIncome(){
        this.income = 0;
        for(Employee item : employeeArrayList)
            if(item.getPosition() instanceof Manager){
                income += ((Manager) item.getPosition()).randomMoney();
            }
    }

    public int getStaffSize(){
        return employeeArrayList.size();
    }
}
```
####Employee.java
```java
package ru.mirea.prak_7and8;
import java.util.Random;

class Employee {
    private String name;
    private String surname;
    private double baseSalary;
    private EmployeePosition position;

    Employee(String name, String surname, int baseSalary, EmployeePosition position){
        this.name = name;
        this.surname = surname;
        this.baseSalary = baseSalary;
        this.position = position;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
}

class Manager implements EmployeePosition{
    private Random random = new Random();
    private int money = 0;

    public int randomMoney(){
        this.money = random.nextInt(140000 - 115000) + 115000;
        return money;
    }

    @Override
    public String getJobTitle() {//должность
        return "Manager";
    }

    @Override
    public double calcSalary(double baseSalary){
        return (baseSalary + money * 0.05);
    }
}

class TopManager implements EmployeePosition{
    Company company;
    public TopManager(Company company){
        this.company = company;
    }

    @Override
    public String getJobTitle() {
        return "Top Manager";
    }

    @Override
    public double calcSalary(double baseSalary){
        if(company.getIncome() >= 10000000){
            return (baseSalary + baseSalary*1.5);
        }
        else{
            return baseSalary;
        }
    }
}

class Operator implements EmployeePosition{
    @Override
    public String getJobTitle() {
        return "Operator";
    }

    @Override
    public double calcSalary(double baseSalary){
        return baseSalary;
    }
}
```
####EmployeePosition.java
```java
package ru.mirea.prak_7and8;

public interface EmployeePosition {
    String getJobTitle();
    double calcSalary(double baseSalary);
}
```
####Loader.java
```java
package ru.mirea.prak_7and8;
import java.util.List;

public class Loader {
    Company company = new Company();
    private int baseSalary = 120000;
    private final String[] name = {"Mark", "Stephen", "Paul", "Jason", "Andrew", "David", "Richard", "Michael", "Robert"};
    private final String[] surname = {"Abramson", "Thomas", "Smith", "Jones", "Williams", "Brown"};

    public Loader(){
        createCompany();
        company.calcIncome();
        showTopSalary(15);
        removeEmployee(); //удаление 50%
        System.out.println("Доход компании " + company.getIncome());
        showTopSalary(15);
        showLowSalary(30);
    }

    public void removeEmployee(){
        System.out.println("Увольнение");
        for(int i = 0; i < company.getStaffSize(); i++){
            company.fire(i);
        }
    }

    public void createCompany(){
        for(int i = 0; i < 180; i++){
            company.hire(new Employee(name[i%9], surname[i%6], baseSalary, new Operator()));
        }
        for(int i = 0; i < 80; i++){
            company.hire(new Employee(name[(i+2)%9], surname[(i+4)%6], baseSalary, new Manager()));
        }
        for(int i = 0; i < 10; i++){
            company.hire(new Employee(name[(i+5)%9], surname[(i+3)%6], baseSalary, new TopManager(company)));
        }
    }

    public void showTopSalary(int number){
        List<Employee> temp;
        temp = company.getTopSalaryStaff(number);
        System.out.println("Топ " + number + " высоких зарплат");
        for (int i = 0; i < number; i++){
            System.out.printf("%,d", (int) temp.get(i).getPosition().calcSalary(temp.get(i).getBaseSalary()));
            System.out.println(" руб\t\t" + temp.get(i).getPosition().getJobTitle());
        }
    }

    public void showLowSalary(int number){
        List<Employee> temp;
        temp = company.getLowestSalaryStaff(number);
        System.out.println("Топ " + number + " низких зарплат");
        for (int i = 0; i < number; i++){
            System.out.printf("%,d", (int) temp.get(i).getPosition().calcSalary(temp.get(i).getBaseSalary()));
            System.out.println(" руб\t\t" + temp.get(i).getPosition().getJobTitle());
        }
    }

    public static void main(String[] args) {
        new Loader();
    }
}
```
####Form.java
```java
package ru.mirea.prak_9and10;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Form extends JFrame{
    static int ftp, stp;
    private JButton button_1 = new JButton("Команда 1");
    private JButton button_2 = new JButton("Команда 2");
    private JButton button_3 = new JButton("Конец матча!");
    private JLabel lable_1 = new JLabel("Result: 0 X 0");
    private JLabel label_2 = new JLabel("Last Scorer: N/A");
    private Label label_3 = new Label("Winner: DRAW");

    public Form(){
        super("Board");
        this.setBounds(150, 200, 750, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        container.add(button_1); //+команда 1
        container.add(button_2); //+команда 2
        container.add(lable_1); //счёт
        container.add(label_2); //последяя забившая команда
        container.add(button_3); //конец матча
        container.add(label_3); //победитель


        button_1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ftp++;
                lable_1.setText("Result: " + Form.getFtp() + " X " + getStp());
                label_2.setText("Last Scorer: Команда 1");
            }
        });

        button_2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stp++;
                lable_1.setText("Result: " + getFtp() + " X " + getStp());
                label_2.setText("Last Scorer: Команда 2");
            }
        });

        button_3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label_3.setText("Winner: " + Form.getWinner());
            }
        });
    }

    static int getStp() {
        return stp;
    }

    static int getFtp() {
        return ftp;
    }

    static String getWinner(){
        if(ftp > stp){ return "Команда 1"; }
        else if(ftp < stp) return "Команда 2";
        else return "Ничья";
    }
}
```
####Loader.java
```java
package ru.mirea.prak_9and10;

public class Loader {
    public static void main(String[] args) {
        Form app = new Form();
        app.setVisible(true);
    }
}
```
