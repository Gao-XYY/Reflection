package com.gao.demo;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 获取当前运行时类的属性结构
 */
public class FieldTest {

    @Test
    public void test1(){
        Class<Person> clazz = Person.class;

        //获取属性结构
        //getFields():获取当前运行时类及其父类中声明为public访问权限的属性
        Field[] fields = clazz.getFields();
        for (Field f: fields) {
            System.out.println(f);
        }
        System.out.println("===============================");

        //getDeclaredFields():获取当前运行时类中声明的所有属性。（不包括父类中声明的属性）
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f: declaredFields) {
            System.out.println(f);
        }

    }

    //权限修饰符   数据类型 变量名 = 。。。
    @Test
    public void test2(){

        Class<Person> clazz = Person.class;
        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field f : declaredFields) {
            //1、权限修饰符
            int modifier = f.getModifiers();
            //这里的modifier是一个int类型的，直接打印结果为数字可以在Modifier类中找到相对应的数字
            //System.out.println(modifier);
            System.out.print(Modifier.toString(modifier) + "\t");

            //2、数据类型
            Class type = f.getType();
            System.out.print(type.getName()  + "\t");

            //3、变量名
            String name = f.getName();
            System.out.println(name);


        }

    }


}
