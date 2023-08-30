package com.kk;

import java.util.Scanner;

/**
 * 对数据进行校验
 */
public class TelNoteRegex {
    /**
     * 对菜单输入选项的验证
     * @param min
     * @param max
     * @return 返回合法的菜单功能输入
     */
    public int menuItemValidate(int min, int max){
        // 定义正则表达式
        String regex = "[1-9]{1}"; // 只能接受1位数字1-9
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("请输入合法菜单项("+min+"-"+max+"): ");
            String input = scanner.next();
            if(input.matches(regex)){
                int i = Integer.parseInt(input);
                if( min <= i && i <= max){
//                    scanner.close();
                    return i;
                }else {
                    // 非法输入二：范围错误
                    System.out.println("非法范围，请输入合法范围数字！");
                }
            }else {
                // 非法输入一：非数字
                System.out.println("非法输入项，请输入数字！");
            }
        }
    }

    /**
     * 对用户输入姓名的验证 大小写字母且1-10字符间
     * @return
     */
    public String nameValidate(){
        String regex = "[a-zA-Z]{1,10}";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("请输入姓名(大小写字母且长度为1-10):");
            String input = scanner.next();
            if (input.matches(regex)){
                return input;
            }else {
                System.out.println("非法输入项，请重新输入！");
            }
        }
    }

    /**
     * 对用户输入的年龄进行验证 年龄在1-99之间
     * @return
     */
    public String ageValidate(){
        String regex = "\\d{1,2}";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("请输入年龄(1-99):");
            String input = scanner.next();
            if (input.matches(regex)){
                int age = Integer.parseInt(input);
                if(1 <= age && age <= 99){
                    return input;
                }else {
                    System.out.println("非法范围，请重新输入！");
                }
            }else {
                System.out.println("非法输入项，请重新输入！");
            }
        }
    }

    /**
     * 对用户输入的性别验证
     * 输入要求：男(m/M) 女(f/F)
     */
    public String sexValidate(){
        String regex = "[mMfF]{1}";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("请输入性别{男(m/M) 女(f/F)} :");
            String input = scanner.next();
            if (input.matches(regex)){
                if (input.equalsIgnoreCase("m")){
                    return "男";
                } else{
                    return "女";
                }
            }else {
                System.out.println("非法输入项，请重新输入！");
            }
        }
    }

    /**
     * 对用户输入电话号码的验证
     * 号码要求：允许带有区号的座机号，允许手机号
     * @return
     */
    public String telNumValidate(){
        // 座机号 以及 手机号(1开头的11位数字)
        String regex = "(\\d{3,4}-\\d{7,8})|([1]{1}\\d{10})";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("请输入电话号码(座机号/手机号): ");
            String input = scanner.next();
            if(input.matches(regex)){
                return input;
            }else {
                System.out.println("非法输入项，请重新输入！");
            }
        }
    }

    /**
     * 对地址验证
     * 地址格式要求：字母或数字，长度1-50
     */
    public String addressValidate(){
        String regex = "[a-zA-z0-9^_]{1,50}";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("请输入地址(长度为1-50的字母或数字): ");
            String input = scanner.next();
            if(input.matches(regex)){
                return input;
            }else {
                System.out.println("非法输入项，请重新输入！");
            }
        }
    }

    //测试类
    public static void main(String[] args) {
        TelNoteRegex tr = new TelNoteRegex();
        /*tr.menuItemValidate(1,6);
        tr.nameValidate();
        tr.ageValidate();*/
        /*tr.telNumValidate();*/
        System.out.println(tr.sexValidate());
//        tr.addressValidate(); // 为什么_可以过
    }

}
