package com.packt.camel.chapter5n;

import java.util.Random;

public class RouterBean {

    public String populate(String body) {
        Random random = new Random();
        int value = random.nextInt(1000);
        if (value >= 500) {
            return "direct:one,direct:two,direct:three";
        } else {
            return "direct:one,direct:two";
        }
    }

}
