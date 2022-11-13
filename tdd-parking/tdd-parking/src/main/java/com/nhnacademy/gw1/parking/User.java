package com.nhnacademy.gw1.parking;

public class User {

  private static long id = 0;
  private long amount; //사용자가 가지고 있는 돈의 양
  private long userId = 0; //사용자를 식별할 고유 id

  public User(long amount){
    id += 1;
    this.userId = id;
    this.amount = amount;
  }

  public long getUserId(){ //사용자의 userId
    return this.userId;
  }

  public long getAmount(){
    return this.amount;
  }
}
