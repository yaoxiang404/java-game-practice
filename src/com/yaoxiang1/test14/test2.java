package com.yaoxiang1.test14;

public class test2 {
    public static void main(String[] args) {
//        System.out.println("Hello, World!");
//        System.exit(0);
//        System.out.println("Hello, World!1");
//        long l = System.currentTimeMillis();
//        System.out.println(l);
        int[] arr1 = {0,1,2,3,4,5,6,7,8,9,10};
        int[] arr2 = new int[10];
        System.arraycopy(arr1, 3, arr2, 5, 8);
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);
        }
    }
}
