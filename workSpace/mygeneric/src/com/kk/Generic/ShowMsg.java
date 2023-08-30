package com.kk.Generic;

public class ShowMsg {
    public void showValue(Generic<? super Integer> generic){
        System.out.println(generic.getValue());
    }
    public static void main(String[] args) {
        ShowMsg showMsg = new ShowMsg();

        // String
        Generic<String> generic1 = new Generic<>();
        generic1.setValue("kk");
        showMsg.showValue(generic1);

        // Integer
        Generic<Integer> generic2 = new Generic<>();
        generic2.setValue(18);
        showMsg.showValue(generic2);

        // Number
        Generic<Number> generic3 = new Generic<>();
        generic3.setValue(28);
        showMsg.showValue(generic3);
    }
}
