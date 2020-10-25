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
