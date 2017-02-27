package com.chao.helper.springboot.model;

/**
 * Created by zl on 2015/8/27.
 */
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("quchao");
        System.out.println(user.toString());;
    }
}
