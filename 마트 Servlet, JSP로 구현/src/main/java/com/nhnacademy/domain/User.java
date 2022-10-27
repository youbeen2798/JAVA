package com.nhnacademy.domain;

public class User {

  private String Id;
  private String pw;
  private int money;
  public User(String Id, String pw, int money){
    this.Id = Id;
    this.pw = pw;
    this.money = money;
  }

  public String getId(){
    return this.Id;
  }

  public String getPw(){
    return this.pw;
  }

  public int getMoney(){
    return this.money;
  }

}
