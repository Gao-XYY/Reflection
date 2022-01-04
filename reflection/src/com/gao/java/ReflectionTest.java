package com.gao.java;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {

    //反射之前对Person的操作
    @Test
    public void test1(){

        //1、创建Person对象
        Person p1 = new Person("张三", 18);

        //2、通过对象，调用其内部的属性和方法
        p1.age = 12;
        System.out.println(p1.toString());

        p1.show();
        //在Person类的外部，不可通过Person类的对象调用类的私有结构
        //比如：name，showNation()，以及私有的构造器

    }

    //反射之后对Person的操作
    @Test
    public void test2() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        //1、通过反射，创建Person类的对象
        Class clazz = Person.class;
        Constructor cons = clazz.getConstructor(String.class, int.class);

        Object obj = cons.newInstance("张三", 12);

        Person p1 = (Person)obj;

        System.out.println(p1.toString());

        //2、通过反射，调用对象指定的属性，方法
        //调属性
        Field age = clazz.getDeclaredField("age");
        age.set(p1,10);
        System.out.println(p1.toString());

        //调方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p1);
        System.out.println("+======================================");

        //通过反射，可以调用Person类的私有结构的，比如：私有的构造器，方法，属性
        //调用私有的构造器
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Object p2 = (Person)cons1.newInstance("Jerry");
        System.out.println(p2);

        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p2,"hanmeimei");
        System.out.println(p2);

        //调用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String)showNation.invoke(p2, "中国");
        System.out.println(nation);
    }
    /*
    关于Java.lang.Class类的理解
    1、类的加载过程：
    程序在经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾),接着我们使用java.exe命令对某个字节码文件进行解释运行。
    相当于将某个字节码文件加载到内存中。此过程就成为类的加载。加载到内存中的类，我们就称为运行时类，此运行时，就作为Class的一个实例。
    2、换句话说，Class的实例就对应着一个运行时类。
    3、加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式来获取此运行时类。
     */

    //获取Class的实例的方式(前三种方式需要掌握)
    @Test
    public void test3() throws ClassNotFoundException {
        //方式一:调用运行时类的属性：.class
        Class clazz1 = Person.class;
        System.out.println(clazz1);
        //方式二:通过运行时类的对象,调用getClass()
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);
        //方式三:调用Class的静态方法：forName(String classPath)
        Class clazz3 = Class.forName("com.gao.reflection.demo.Person");
        System.out.println(clazz3);

        //方式四:使用类的加载器：ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.gao.reflection.demo.Person");
        System.out.println(clazz4);

    }
    /*
    那些类型可以有Class对象
    (1)、class:外部类，成员(成员内部类，静态内部类),局部内部类，匿名内部类
    (2)、interface:接口
    (3)、enum:枚举
    (4)、[]:数组
    (5)、annotation:注解@interface
    (6)、primitive type:基本数据类型
    (7)、void
     */

    //Class实例可以是那些结构的说明：
    @Test
    public void test4(){
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        //只要元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }


}
