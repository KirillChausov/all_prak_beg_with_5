package ru.mirea.prak_7_2;

public class CPrinter implements handleEmployees{
    @Override
    public void handleEmployees(Employee employee) {
        System.out.println("-----------");
        System.out.println(employee);
        System.out.println("-----------");
    }
}
