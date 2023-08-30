package com.kk.Map;

import java.util.Objects;

public class User  {
    private String name;
    private int age;

    public User(){}
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }


    @Override
    public String toString() {
        return name + " " + age + "  " ;
    }

//    @Override
//    public int compareTo(User o) {
//        // 如果左边对象大于右边对象返回正整数
//        // 如果左边对象小于右边对象返回负整数
//        // 如果相等返回0
////        if(this.age != o.getAge())
////        {
////            return this.age - o.getAge();
////        } else {
////            return (this.getName().compareTo(o.getName()));
////        }
//        if (this.age > o.getAge()){
//            return 1;
//        }else if ( this.age == o.getAge() ){
//            return this.getName().compareTo(o.getName());
//        }
//        return -1;
//    }
    public int compareTo(User o) {
        if (this.age != o.getAge()) {
            return this.age - o.getAge(); // 按年龄升序排列
        } else {
            return this.getName().compareTo(o.getName()); // 年龄相同时按姓名升序排列
        }
    }
}
