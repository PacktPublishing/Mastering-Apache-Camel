package com.packt.camel.chapter3;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

    public void configure() {
        from("timer:fire?period=5000")
                .setBody(constant("Hello Chapter3"))
                .process(new PrefixerProcessor())
                .to("log:MyRoute")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        String body = exchange.getIn().getBody(String.class);
                        if (body.startsWith("Prefixed ")) {
                            body = body.substring("Prefixed ".length());
                            exchange.getIn().setBody(body);
                        }
                    }
                })
                .to("log:MyRoute");
    }

}
