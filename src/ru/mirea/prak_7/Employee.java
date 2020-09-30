package ru.mirea.prak_7;
import java.util.List;

abstract class Employee implements EmployeePosition{
    private String surname;
    private String name;
    private String salary;
    private String position;
    public List<Employee> employees;

    Employee(String surname, String name){
        this.surname = surname;
        this.name = name;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}

class Manager extends Employee{
    Manager(String surname, String name){
        super(surname, name);

    }

    @Override
    public String getJobTitle() {//должность
        return "Manager";
    }

    public double calcSalary(double baseSalary){//з.п.
        return 1;
    }
}

class TopManager extends Employee{
    TopManager(String surname, String name){
        super(surname, name);
    }

    @Override
    public String getJobTitle() {
        return "Top Manager";
    }

    public double calcSalary(double baseSalary){
        return 1;
    }
}

class Operator extends Employee{
    Operator(String surname, String name){
        super(surname, name);
    }

    @Override
    public String getJobTitle() {
        return "Operator";
    }

    public double calcSalary(double baseSalary){
        return 1;
    }
}