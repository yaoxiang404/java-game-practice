package com.yaoxiang1.test14;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数：");
        int num1 = scanner.nextInt();
        int num = num1;
        int len = (num1+"").length();
        int sum = 0;
        while (num1 !=0){
            int lastNumber = num1 % 10;
            num1 = num1 / 10;
            System.out.println(lastNumber);
            sum = (int)(sum+Math.pow(lastNumber,len));
        }
        if(sum == num){
            System.out.println("是");
        }else{
            System.out.println("不是");
        }
    }
}
