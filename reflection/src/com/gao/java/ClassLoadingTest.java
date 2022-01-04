package com.gao.java;

/**
 * 类的加载与ClassLoader的理解
 */
public class ClassLoadingTest {

    public static void main(String[] args) {
        System.out.println(A.m);
    }

}
class A{
    static {
        m = 300;
    }
    static int m = 100;
}
/*
    第二步:链接结束后m=0
    第三步:初始化后，m的值<clinit>()方法执行决定
        这个A的类构造器<clinit>()方法由类变量的赋值和静态代码块中的语句按照顺序合并产生，类似于
        <clinit>(){
            m = 300;
            m = 100;
        }
 */
