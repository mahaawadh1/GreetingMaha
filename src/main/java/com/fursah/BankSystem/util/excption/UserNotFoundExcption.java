package com.fursah.BankSystem.util.excption;

public class UserNotFoundExcption extends RuntimeException{
    public UserNotFoundExcption (String str){
        super(str);
    }
}
