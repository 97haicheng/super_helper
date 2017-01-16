package com.chao.helper.cvs;

/**
 * Created by think on 2016/11/3.
 */
public class Student {

    private String name;

    private String sex;
    private int age;

    public Student() {
    }

    public Student(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student [toString()=" + this.name + "-->" + this.sex + "-->"
                + this.age + "]";
    }
}
