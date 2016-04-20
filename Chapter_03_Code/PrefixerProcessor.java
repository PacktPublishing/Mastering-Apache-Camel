package com.packt.camel.chapter3;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrefixerProcessor implements Processor {

    private final static Logger LOGGER = LoggerFactory.getLogger(PrefixerProcessor.class);

    public void process(Exchange exchange) throws Exception {
        String inBody = exchange.getIn().getBody(String.class);
        LOGGER.info("Received in message with body {}", inBody);
        LOGGER.info("Prefixing body ...");
        inBody = "Prefixed " + inBody;
        exchange.getIn().setBody(inBody);
    }

}
