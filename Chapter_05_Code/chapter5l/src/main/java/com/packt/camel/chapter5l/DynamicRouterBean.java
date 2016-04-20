package com.packt.camel.chapter5l;

import java.util.Random;

public class DynamicRouterBean {

    public String slip(String body) {
        Random random = new Random();
        int value = random.nextInt(1000);
        if (value >= 500) {
            return "direct:large";
        } else {
            return "direct:small";
        }
    }

}
