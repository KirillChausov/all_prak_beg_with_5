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
