package com.yaoxiang1.test14;

public class test {
    public static void main(String[] args) {
        //Math
        //绝对值abs
        int numm1 = Math.abs(-10);
        System.out.println(numm1);
        //向上取整ceil
        double num2 = Math.ceil(3.14);
        System.out.println(num2);
        //向下取整floor
        double num3 = Math.floor(3.14);
        System.out.println(num3);
        //四舍五入round
        long num4 = Math.round(3.14);
        System.out.println(num4);
        //获得2个int的最大值
        int num5 = Math.max(10, 20);
        System.out.println(num5);
        //获得2个int的最小值
        int num6 = Math.min(10, 20);
        System.out.println(num6);
        //随机数
        double num7 = Math.random();
        System.out.println(num7);
        //平方根sqrt
        double num8 = Math.sqrt(9);
        System.out.println(num8);
        //幂运算pow
        double num9 = Math.pow(2, 3);
        System.out.println(num9);
    }
}
