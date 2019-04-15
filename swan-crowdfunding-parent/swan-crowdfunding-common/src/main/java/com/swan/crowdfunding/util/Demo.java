package com.swan.crowdfunding.util;

import java.util.Scanner;

public class Demo {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
         test2();
    }

    public static void test2() {
        boolean bool = true;
         do{
            System.out.println("请输入天气的第一个字母：");
            String util = util(scanner.next().trim().charAt(0));
            System.out.println(util);
            System.out.print("你想继续吗？(y/n)");
            String meg = scanner.next();
            bool = meg.equals("y")?true:false;
        }while (bool);
        System.out.println("退出系统！");
    }

    public static String util(char c) {
        switch (c) {
        case 'D':
            return "干燥";
        case 'M':
            return "潮湿";
        case 'H':
            return "炎热";
        case 'R':
            return "下雨";
        default : 
            return "输入错误，无法转换";
        }
        
    }

    public void test1() {

        int[] number = new int[10];
        int positive = 0;
        int minus = 0;
        int zero = 0;
        for (int i = 0; i < number.length; i++) {
            System.out.println("请输入第" + i + "个数：");
            int num = scanner.nextInt();
            number[i] = num;
            if (number[i] < 0) {
                minus++;
            } else if (num > 0) {
                positive++;
            } else {
                zero++;
            }
        }

        System.out.println("负数：" + minus + "个\t正数：" + positive + "个\t零有：" + zero + "个");

    }

}
