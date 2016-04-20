package com.packt.camel.chapter5s;

import java.util.Random;

public class RoutingSlipBean {

    public String nextSteps(String body) {
        Random random = new Random();
        int value = random.nextInt(1000);
        if (value >= 500) {
            return "direct:one,direct:two";
        } else {
            return "direct:one";
        }
    }

}
