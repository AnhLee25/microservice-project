package com.shop.productservice;

import org.junit.Test;

public class PassByValueUnitTest {

  public static void modify(int x1, int y1) {
    x1 = 5;
    y1 = 10;
  }

  public static void modify(Stuff a1, Stuff b1) {
    System.out.println("a1 hashcode: " + a1.hashCode() + ", b1 hashcode: " + b1.hashCode());

    a1.num++;

    b1 = new Stuff(1);
    b1.num++;
    System.out.println("a1 hashcode: " + a1.hashCode() + ", b1 hashcode: " + b1.hashCode());
  }

  @Test
  public void whenModifyingPrimitives_thenOriginalValuesNotModified() {

    int x = 1;
    int y = 2;

    // Before Modification
    System.out.println("x:" + x + ", y: " + y);

    modify(x, y);

    // After Modification
    System.out.println("x:" + x + ", y: " + y);
  }

  @Test
  public void whenModifyingObjects_thenOriginalObjectChanged() {
    Stuff a = new Stuff(1);
    Stuff b = new Stuff(1);

    // Before Modification
    System.out.println("a hashcode: " + a.hashCode() + ", b hashcode: " + b.hashCode());
    System.out.println("Before ---- a: " + a.num);
    System.out.println("Before ---- b: " + b.num);
    modify(a, b);

    // After Modification
    System.out.println("After ---- a: " + a.num);
    System.out.println("After ---- b: " + b.num);
  }
}

class Stuff {
  public int num;

  public Stuff(int num) {
    this.num = num;
  }
}
