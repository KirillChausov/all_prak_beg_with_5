package ru.mirea.prak_4;

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
                if(temp.get(j).getPosition().calcSalary(temp.get(j).getBaseSalary()) > temp.get(j).getPosition().calcSalary(temp.get(j).getBaseSalary())) {
                    x = temp.get(j);
                    temp.set(j, temp.get(j));
                    temp.set(j, x);
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
                if(temp.get(j).getPosition().calcSalary(temp.get(j).getBaseSalary()) > temp.get(j).getPosition().calcSalary(temp.get(j).getBaseSalary())) {
                    x = temp.get(j);
                    temp.set(j, temp.get(j));
                    temp.set(j, x);
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
