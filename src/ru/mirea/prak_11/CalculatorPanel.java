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
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(calculator, "Некорректный ввод!");
                }
                result.setText(String.valueOf(n_1 + " / " + n_2 +" = " + ((double)n_1 / n_2)));
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
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(calculator, "Некорректный ввод!");
                }
                result.setText(String.valueOf(n_1 + " % " + n_2 +" = " + ((int)n_1 % n_2)));
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
