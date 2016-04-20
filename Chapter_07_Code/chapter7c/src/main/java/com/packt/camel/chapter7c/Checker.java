package com.packt.camel.chapter7c;

public class Checker {

    public String validate(String body) throws Exception {
        String[] param = body.split("=");
        if (param.length != 2) {
            throw new IllegalArgumentException("Bad parameter");
        }
        if (!param[0].equalsIgnoreCase("message")) {
            throw new IllegalArgumentException("Message parameter expected");
        }
        return "Hello " + param[1] + "\n";
    }

}
