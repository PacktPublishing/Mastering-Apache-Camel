package com.packt.camel.component;

import org.apache.camel.Component;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultEndpoint;

public class PacktEndpoint extends DefaultEndpoint {

    public PacktEndpoint(String uri, Component component) {
        super(uri, component);
    }

    public PacktProducer createProducer() {return new PacktProducer(this);
    }

    public PacktConsumer createConsumer(Processor processor) throws Exception {
        return new PacktConsumer(this, processor);
    }

    public boolean isSingleton() {
        return false;
    }

}
