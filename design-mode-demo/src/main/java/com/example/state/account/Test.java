package com.example.state.account;

/**
 * @author Javen
 * @date 2024/2/14
 */
public class Test {

    public static void main(String[] args) {
        Account account = new Account("Javen", 0);
        account.deposit(1000);
        account.withdraw(2000);
        account.deposit(3000);
        account.withdraw(4000);
        account.withdraw(1000);
        account.computeInterest();
    }
}
