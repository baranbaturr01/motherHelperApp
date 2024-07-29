package com.baranbatur.newMotherHelper;

public class main {

    public static void main(String[] args) {
        String str1 = "selam";
        String str2 = "selam";
        String str3 = new String("selam");
        String str4 = new String("selam");

        System.out.println(str1==str2);
        System.out.println(str1.equals(str2));
        System.out.println(str1==str3);
        System.out.println(str3.equals(str4));

    }
}
