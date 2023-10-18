package com.shop.productservice;

public class Testt {
  public static void main(String[] args) {
    Human man = new Man();
    man.rename();

    if (man instanceof Man){
      Man man1 = (Man) man;
    }
  }

  static class Human {
    String name;
    public void rename(){
      System.out.println("Rename from parent");
    }
  }

  static class Man extends Human{
    @Override
    public void rename() {
      System.out.println("Rename from child");
    }
  }

}
