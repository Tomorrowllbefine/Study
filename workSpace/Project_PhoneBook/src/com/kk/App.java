package com.kk;

import java.util.Scanner;

/**
 * 电话本项目入口类
 */
public class App {

    /**
     * 启动电话本项目
     * @param args
     */
    public static void main(String[] args) {
        start();
    }


        /**
         * 控制主菜单
         */
        public static void start () {

            Menu menu = new Menu();
            Operate operate = new Operate();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                menu.mainMenu();
                System.out.println("请输入对应功能编号: ");
                int num = scanner.nextInt();
                switch (num) {
                    case 1: // 添加记录
                        operate.addLogic();
                        break;
                    case 2: // 查找记录
                        operate.searchLogic();
                        break;
                    case 3: // 修改记录
                        operate.modifyLogic();
                        break;
                    case 4: // 删除记录
                        operate.delLogic();
                        break;
                    case 5: // 排序记录
                        operate.orderLogic();
                        break;
                    case 6: // 正常退出系统
                        System.out.println("【提示】系统结束.....");
                        System.exit(0);
                        break;
                }
            }
        }
    }

