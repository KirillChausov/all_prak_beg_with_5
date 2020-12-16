package ru.mirea.prak_25_26;

public class Actor {
    private int index;
    private int year;
    private int age;
    private String name;
    private String movie;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    public String getMovie() {
        return movie;
    }
    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "index=" + index +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", movie='" + movie + '\'' +
                '}' + "\n";
    }
}