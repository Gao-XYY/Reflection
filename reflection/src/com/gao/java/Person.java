package com.gao.java;

public class Person {

    private String name;

    public int age;

    public Person() {
        System.out.println("Person");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void show(){
        System.out.println("你好,我是一个人");
    }

    private String showNation(String nation){
        System.out.println("我的国际是:" + nation);
        return nation;
    }

}
