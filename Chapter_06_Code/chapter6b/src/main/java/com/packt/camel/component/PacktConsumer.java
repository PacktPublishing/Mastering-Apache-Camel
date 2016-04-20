package com.packt.camel.component;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;
import org.apache.camel.impl.DefaultExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PacktConsumer extends DefaultConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(PacktConsumer.class);

    private ServerSocket serverSocket;

    public PacktConsumer(Endpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);
        serverSocket = new ServerSocket(4444);
        LOGGER.debug("Creating Packt Consumer ...");
    }

    @Override
    protected void doStart() throws Exception {
        LOGGER.debug("Starting Packt Consumer ...");
        new Thread(new AcceptThread()).start();
        super.doStart();
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();
        LOGGER.debug("Stopping Packt Consumer ...");
        if (serverSocket != null) {
            serverSocket.close();
        }
    }

    class AcceptThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                // create the exchange
                Exchange exchange = new DefaultExchange(getEndpoint(), ExchangePattern.InOut);
                Socket clientSocket = null;
                try {
                    clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String inputLine = in.readLine();
                    if (inputLine != null) {
                        LOGGER.debug("Get input line: {}", inputLine);
                        exchange.getIn().setBody(inputLine, String.class);
                        // send the exchange to the next processor
                        getProcessor().process(exchange);
                        // get out message
                        String response = exchange.getOut().getBody(String.class);
                        if (response == null) {
                            response = exchange.getIn().getBody(String.class);
                        }
                        if (response != null) {
                            out.println(response);
                        }
                    }
                } catch (Exception e) {
                    exchange.setException(e);
                } finally {
                    if (clientSocket != null) {
                        try {
                            clientSocket.close();
                        } catch (Exception e) {
                            // nothing to do
                        }
                    }
                }
            }
        }

    }

}
