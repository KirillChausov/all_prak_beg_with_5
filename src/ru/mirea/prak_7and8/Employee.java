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