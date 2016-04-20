package com.packt.camel.chapter4c;

public class SimpleBean {

    public static String hello(String message) {
        System.out.println("Hello " + message);
        return "Hello" + message;
    }

}
