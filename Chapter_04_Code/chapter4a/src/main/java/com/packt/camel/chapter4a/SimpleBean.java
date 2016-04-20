package com.packt.camel.chapter4a;

public class SimpleBean {

    public static String hello(String message) {
        System.out.println("Hello " + message);
        return "Hello" + message;
    }

}
