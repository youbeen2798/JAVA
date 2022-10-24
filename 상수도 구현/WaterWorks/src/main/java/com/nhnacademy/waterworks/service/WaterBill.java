package com.nhnacademy.waterworks.service;

import javax.print.DocFlavor.STRING;

public class WaterBill {
  private String city;
  private String sector;
  private int unitPrice;
  private long billTotal;

  public WaterBill(String city, String sector, int unitPrice, long billTotal){
    this.city = city;
    this.sector = sector;
    this.unitPrice = unitPrice;
    this.billTotal = billTotal;
  }

  public String getCityName(){
    return this.city;
  }

  public String getSectorName(){
    return this.sector;
  }

  public int getUnitPrice(){
    return this.unitPrice;
  }

  public long getBillTotal(){
    return this.billTotal;
  }

  @Override
  public String toString(){
    return getClass().getSimpleName()
        + " { city = " + getCityName() + ", "
        + " sector = " + getSectorName() + ", "
        + "unitPrice = " + getUnitPrice() + ", "
        + "billTotal = " + getBillTotal() + " } ";
  }

}
