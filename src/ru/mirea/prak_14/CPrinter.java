package ru.mirea.prak_14;

public class CPrinter implements handleEmployees{
    @Override
    public void handleEmployees(Employee employee) {
        System.out.println("-----------");
        System.out.println(employee);
        System.out.println("-----------");
    }
}
