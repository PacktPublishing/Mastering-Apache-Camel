package com.packt.camel.component;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PacktProducer extends DefaultProducer {

    private final static Logger LOGGER = LoggerFactory.getLogger(PacktProducer.class);

    public PacktProducer(Endpoint endpoint) {
        super(endpoint);
        LOGGER.debug("Creating Packt Producer ...");
    }

    public void process(Exchange exchange) throws Exception {
        LOGGER.debug("Processing exchange");
        String input = exchange.getIn().getBody(String.class);
        LOGGER.debug("Get input: {}", input);
        LOGGER.debug("Connecting to socket on localhost:4444");
        Socket socket = new Socket("localhost", 4444);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println(input);
        String fromServer = in.readLine();
        LOGGER.debug("Get reply from server: {}", fromServer);
        LOGGER.debug("Populating the exchange");
        exchange.getIn().setBody(fromServer, String.class);
    }


}
