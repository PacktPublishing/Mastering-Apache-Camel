package com.packt.camel.chapter5v;

import java.util.Random;

public class DelayBean {

    public int delay() {
        Random random = new Random();
        return random.nextInt(10000);
    }

}
