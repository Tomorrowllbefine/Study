package com.kk.Chapter4;

import java.util.concurrent.atomic.AtomicInteger;

public class AccountSafe implements Account{

    private AtomicInteger balance;

    public static void main(String[] args) {
        Account.demo(new AccountSafe(10000));
    }

    public AccountSafe(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {

        while(true){
            int prev = balance.get();
            int next = prev - amount;
            if(balance.compareAndSet(prev, next)){
                break; // 正常删减
            }
        }
    }
}
