package ru.mirea.prak_7_2;

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
