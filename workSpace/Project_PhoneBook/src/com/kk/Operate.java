package com.kk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 核心业务类
 * Logic：业务逻辑控制，负责与用户交互
 * Operation：业务方法，负责接受调用并完成实际单个功能
 */
public class Operate {

    private List<Person> list;
    public Operate(){
        this.list = new ArrayList<>();
    }
    Menu menu = new Menu(); // 全局菜单
    TelNoteRegex tr = new TelNoteRegex(); // 全局校验器

    /**
     * 用户添加记录 业务逻辑控制
     */
    public void addLogic(){
        while(true){
            menu.addMenu();
            System.out.println("请输入对应功能编号: ");
            int num = tr.menuItemValidate(1,3);
            switch (num){
                case 1: // 添加新记录
                    boolean res = this.addOperation();
                    if(res) System.out.println("【提示】添加成功！");
                    else System.out.println("【提示】添加失败！");
                    break;
                case 2: // 查看全记录
                    this.showAll();
                    break;
                case 3: // 返回上一级
                    return;
            }
        }
    }

    /**
     * 查找记录 业务逻辑控制
     */
    public void searchLogic(){
        while(true){
            menu.searchMenu();
            System.out.println("请输入对应功能编号: ");
            int num = tr.menuItemValidate(1,7);
            switch (num){
                case 1: // 按姓名查找
                    this.searchByName();
                    System.out.println("【提示】查找完成！");
                    break;
                case 2: // 按电话号码查找
                    this.searchByTelNum();
                    System.out.println("【提示】查找完成！");
                    break;
                case 3: // 按住址查找
                    this.searchByAdd();
                    System.out.println("【提示】查找完成！");
                    break;
                case 4: // 查找全记录
                    this.showAll();
                    System.out.println("【提示】查找完成！");
                    break;
                case 5: // 返回上一级
                    return;
            }
        }
    }

    /**
     * 修改记录 业务逻辑控制
     */
    public void modifyLogic(){
        while(true){
            menu.modifyMenu();
            System.out.println("请输入对应功能编号: ");
            int num = tr.menuItemValidate(1, 3);
            switch(num){
                case 1: //查看全记录
                    this.showAll();
                    break;
                case 2: // 修改制定记录
                    this.modifyOperation();
                    System.out.println("【提示】修改完成！");
                    break;
                case 3: // 返回上一级
                    return;
            }
        }
    }

    /**
     * 删除记录 业务逻辑控制
     */
    public void delLogic(){
        while(true){
            menu.delMenu();
            System.out.println("请输入对应功能编号: ");
            int num = tr.menuItemValidate(1, 4);
            switch(num){
                case 1: // 查看全记录
                    this.showAll();
                    break;
                case 2: // 删除指定记录
                    this.delOperation();
                    System.out.println("【提示】删除完成！");
                    break;
                case 3: // 删除全部记录
                    this.delAllOperation();
                    System.out.println("【提示】删除完成！");
                    break;
                case 4: // 返回上一级
                    return;
            }
        }
    }

    /**
     * 排序记录 业务逻辑控制
     */
    public void orderLogic(){
        while(true){
            menu.orderMenu();
            System.out.println("请输入对应功能编号: ");
            int num = tr.menuItemValidate(1, 4);
            switch(num) {
                case 1: // 按姓名排序
                    this.orderByName();
                    break;
                case 2: // 按年龄排序
                    this.orderByAge();
                    break;
                case 3: // 查看全部记录
                    this.showAll();
                    break;
                case 4: // 返回上一级
                    return;
            }
        }
    }


    /**
     * 添加新记录信息
     */
    public boolean addOperation(){
        // 获取用户数据
        String name = tr.nameValidate();
        String age = tr.ageValidate();
        String sex = tr.sexValidate();
        String telNum = tr.telNumValidate();
        String addr = tr.addressValidate();

        // 实例化新的用户对象
        Person person = new Person(name,age,sex,telNum,addr);
        person.setId(this.list.size()+1);
        list.add(person);
        return true;
    }


    //查询全部记录
    public void showAll(){
        if( list.size() == 0) System.out.println("【提示】当前电话本为空！");
        else {
            for (Person p : list) {
                System.out.println(p);
            }
        }
    }

    //按姓名查询
    public void searchByName(){
        String name = tr.nameValidate();
        boolean notFound = true;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equals(name)){
                System.out.println(list.get(i));
                notFound = false;
            }
        }
        if (notFound) System.out.println("【提示】电话表中未有此记录！");
    }


    //按电话号码查询
    public void searchByTelNum(){
        String tel = tr.telNumValidate();
        boolean notFound = true;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getTelNum().equals(tel)){
                System.out.println(list.get(i));
                notFound = false;
            }
        }
        if (notFound) System.out.println("【提示】电话表中未有此记录！");
    }

    //按地址查询记录
    public void searchByAdd(){
        String addr = tr.addressValidate();
        boolean notFound = true;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getAddress().equals(addr)){
                System.out.println(list.get(i));
                notFound = false;
            }
        }
        if (notFound) System.out.println("【提示】电话表中未有此记录！");
    }

    /**
     * 修改指定记录
     */
    public void modifyOperation(){
        System.out.println("请输入待修改用户序号: ");
        int num1 = tr.menuItemValidate(1,list.size());

        while(true){
            menu.subModifyMenu();
            System.out.println("请输入对应功能编号: ");
            int num2 = tr.menuItemValidate(1,6);
            switch(num2){
                case 1: // 修改姓名
                    String name = tr.nameValidate();
                    list.get(num1-1).setName(name);
                    break;
                case 2: // 修改年龄
                    String age = tr.ageValidate();
                    list.get(num1-1).setAge(age);
                    break;
                case 3: // 修改性别
                    String sex = tr.sexValidate();
                    list.get(num1-1).setSex(sex);
                    break;
                case 4: // 修改号码
                    String tel = tr.telNumValidate();
                    list.get(num1-1).setTelNum(tel);
                    break;
                case 5: // 修改住址
                    String addr = tr.addressValidate();
                    list.get(num1-1).setAddress(addr);
                    break;
                case 6: // 返回
                    return;
            }
            System.out.println("【提示】修改后的用户信息如下: ");
            System.out.println(list.get(num1-1));
        }
    }

    /**
     * 删除指定记录
     */
    public void delOperation(){
        if (list.size() == 0) System.out.println("【提示】当前电话本为空！");
        else {
                System.out.println("请输入待删除用户序号: ");
                int num1 = tr.menuItemValidate(1,list.size());
                list.remove(num1-1); // 删除
                // 整理序号
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setId(i+1);
                }
        }
    }

    /**
     * 删除全部记录
     */
    public void delAllOperation(){
        if (list.size() == 0) System.out.println("【提示】当前电话本为空！");
        else list.clear();
    }

    /**
     * 按用户姓名排序记录
     */
    public void orderByName(){
        list.sort(new NameComparator());
        // 整理序号
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(i+1);
        }
    }

    /**
     * 按用户年龄排序记录
     */
    public void orderByAge(){
        list.sort(new AgeComparator());
        // 整理序号
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(i+1);
        }
    }

    /**
     * 实现按姓名排序的比较器
     */
    class NameComparator implements Comparator<Person>{

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    /**
     * 实现按年龄排序的比较器
     */
    class AgeComparator implements Comparator<Person>{

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getAge().compareTo(o2.getAge());
        }
    }
}
