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
