package com.packt.camel.chapter5e;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PrependProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        String inBody = exchange.getIn().getBody(String.class);
        inBody = "Hello " + inBody;
        exchange.getIn().setBody(inBody);
    }

}
