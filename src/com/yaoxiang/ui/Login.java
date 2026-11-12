package com.yaoxiang.ui;

import com.yaoxiang.domain.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Login {
     public  void start(){

         System.out.println("游戏的登录注册界面");
         ArrayList<User>list=new ArrayList<>();
         while (true) {
             System.out.println("╔════════════════════════════════╗");
             System.out.println("    🎮 欢迎来到文字格斗游戏 🎮   ");
             System.out.println("╚════════════════════════════════╝");
             System.out.println("请选择操作：1登录 2注册 3退出");
             Scanner sc = new Scanner(System.in);
             String choose = sc.next();
             switch(choose){
                 case"1"->lonin(list);
                 case"2"->zhuce(list);
                 case"3"->{
                     System.out.println("用户选择退出操作");
                     System.exit(0);
                 }
                 default->System.out.println("输入错误");
             }
         }
     }

     public static void zhuce(ArrayList<User> list) {
         System.out.println("用户选择注册操作");
         User u=new User();
         Scanner sc=new Scanner(System.in);
         while (true) {
             System.out.println("请输入用户名");
             String username=sc.next();
             //长度必须在3 ~ 16位
             if(!checkLen(3,16,username)){
                 System.out.println("用户名长度必须在3~16位");
                 continue;
             }
             //只能由字母、数字组成，不能是纯数字
             if(!checkUsername(username)){
                 System.out.println("用户名只能由字母、数字组成，不能是纯数字");
                 continue;
             }
             //用户名唯一
             if(contains(list,username)){
                 System.out.println("用户名已存在");
                 continue;
             }
             //当代码执行到这里，说明用户名合法
             u.setName(username);
             //长度必须在3 ~ 16位
             if(!checkLen(3,16,username)){
                 System.out.println("用户名长度必须在3~16位");
                 continue;
             }
             //当代码执行到这里，说明用户合法
             u.setName(username);
             break;
         }
         while (true) {
             System.out.println("请输入密码");
             String password=sc.next();
             //长度3 ~ 8位
             //只能是字母加数字的组合，不能有其他字母
             if(!checkLen(3,8,password)){
                 System.out.println("密码长度必须在3~8位");
                 continue;
             }
             if(!checkUserPassword(password)){
                 System.out.println("密码只能是字母加数字的组合，不能有其他字符");
                 continue;
             }
             System.out.println("请再次输入密码");
             String password2=sc.next();
             if(!password.equals(password2)){
                 System.out.println("两次输入的密码不一致，请重新输入");
                 continue;
             }
             //把密码设置到对象之中
             u.setPassword(password);
             break;
         }
         list.add(u);
         System.out.println("用户："+u.getName()+"注册成功");
     }
    public static void lonin(ArrayList<User> list) {
        System.out.println("用户选择登陆操作");
        //用户名如果未注册提示：用户名未注册，请先注册
	    //用户被锁定提示：用户xxx已经锁定，请联系黑马程序员官方客服：XXX-XXXXX
        //验证码错误提示：验证码输入错误，请重新输入，并生成一个新的验证码
        //判断用户名和密码是否正确，有3次机会，满3次账户锁定。
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("请输入用户名");
            String username = sc.next();
            //判断用户是否存在
            if (!contains(list, username)) {
                System.out.println("用户名"+username+"未注册，请先注册");
                return;
            }
            //用户被锁定提示：用户xxx已经锁定，请联系黑马程序员官方客服：XXX-XXXXX
            int index = findIndex(list,username);
            User u =list.get(index);
            if(!u.isStatus()){
                System.out.println("用户"+username+"已经锁定，请联系黑马程序员官方客服：XXX-XXXXX");
                return;
            }
            //验证码错误提示：验证码输入错误，请重新输入，并生成一个新的验证码
            String rightPassword = u.getPassword();
            for (int i = 0; i <3 ; i++) {
                System.out.println("请输入密码");
                String password = sc.next();

                while (true) {
                    String rightCode = getCode();
                    System.out.println("验证码为：" + rightCode);
                    System.out.println("请输入验证码");
                    String code = sc.next();
                    if (rightCode.equalsIgnoreCase(code)) {
                        System.out.println("验证码正确");
                        break;
                    } else {
                        System.out.println("验证码输入错误，请重新输入");
                    }
                }

                //验证密码
                if(rightPassword.equals(password)) {
                    System.out.println("用户：" + username + "登录成功,开始游戏吧");
                    FightingGame fg=new FightingGame();
                    fg.gameStart(username);
                    break;
                } else {
                    if(i == 2){
                        u.setStatus(false);
                        System.out.println("用户："+username+"账户被锁定");
                        return;
                    }else{
                        System.out.println("密码错误,还剩下" + (2 - i) + "次机会");
                    }
                    continue;
                }
            }
        }
    }
     public static void exit(String[] args) {
         System.out.println("用户选择退出操作");
     }
     //判断用户名长度是否合法
     public static boolean checkLen(int minLen, int maxLen, String str){
         return str.length() >= minLen && str.length() <= maxLen;
     }
     public static int[] getCount(String username){
         int charCount = 0;
         int munCount = 0;
         int otherCount = 0;

         for (int i = 0; i < username.length(); i++) {
             char ch = username.charAt(i);
             if (ch>= 'a' && ch <= 'z'||ch >= 'A' && ch <= 'Z') {
                 charCount++;
             } else if (ch >= '0' && ch <= '9') {
                 munCount++;
             } else {
                 otherCount++;
             }
         }
         return new int[]{charCount,munCount,otherCount};
     }
    //只能由字母、数字组成，不能是纯数字
     public static boolean checkUsername(String username) {
         int[] arr= getCount(username);
         return arr[0] > 0 && arr[1] >= 0 && arr[2] == 0;
     }
    public static boolean checkUserPassword(String password) {
         int[] arr= getCount(password);
         return arr[0] > 0 && arr[1] >0 && arr[2] == 0;
    }
     //判断用户名是否唯一
     public static boolean contains(ArrayList<User> list, String username) {
         for (int i = 0; i < list.size(); i++) {
             User u=list.get(i);
             if(u.getName().equals(username)){
                 return true;
             }
         }
         return false;
     }
     //获取验证码
      public static String getCode(){
        //长度为5
        //由4位大写或者小写字母和1位数字组成，同一个字母可重复
        //数字可以出现在任意位置
         ArrayList<Character> list = new ArrayList<>();
         for(int i = 0;i < 26;i++) {
             list.add((char)('a'+i));
             list.add((char)('A'+i));
         }
        //随机从集合抽字母
         StringBuilder sb = new StringBuilder();
         Random r = new Random();
         for (int i = 0;i < 4;i++) {
             int index = r.nextInt(list.size());
             char c = list.get(index);
             sb.append(c);
         }
        //随机生成数字
         sb.append(r.nextInt(10));
        //数字位置随机
        //先把sb变成字符串，调用字符串的toCharArray方法，转换成字符数组
         char[] arr = sb.toString().toCharArray();
        //随机生成一个位置，用这个位置的数字替换到随机位置上
         int i = r.nextInt(arr.length-1);
        //交换
         char temp = arr[i];
         arr[i] = arr[arr.length-1];
         arr[arr.length-1] = temp;
        //把字符数组转换成字符串
         String code = new String(arr);
         return code;
    }
    //是否禁用
     public static int findIndex(ArrayList<User> list,String username){
         for (int i = 0; i < list.size(); i++) {
             User u=list.get(i);
             if(u.getName().equals(username)){
                 return i;
             }
         }
         return -1;
    }
}
