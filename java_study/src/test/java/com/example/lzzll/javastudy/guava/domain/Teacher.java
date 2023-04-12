package com.example.lzzll.javastudy.guava.domain;

/**
 * @Author lf
 * @Date 2019/9/25 17:54
 * @Description:
 */
public class Teacher {
    private String name;
    private int age;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

   /* @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", location='" + location + '\'' +
                '}';
    }*/

    public Teacher(String name, int age, String location) {
        this.name = name;
        this.age = age;
        this.location = location;
    }

    public Teacher() {
    }
}
