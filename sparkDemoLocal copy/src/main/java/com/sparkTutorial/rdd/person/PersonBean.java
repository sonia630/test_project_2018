package main.java.com.sparkTutorial.rdd.person;

public class PersonBean {
    private String name;
    private String sex;
    private int age;
    private int income;

    public PersonBean(String name, String sex, int age, int income) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "PersonBean{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", income=" + income +
                '}';
    }
}
