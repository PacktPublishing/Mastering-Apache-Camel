package com.packt.camel.component;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PacktComponent extends DefaultComponent {

    private final static Logger LOGGER = LoggerFactory.getLogger(PacktComponent.class);

    public PacktComponent() {
        LOGGER.debug("Creating Packt Camel Component");
    }

    public PacktComponent(CamelContext camelContext) {
        super(camelContext);
        LOGGER.debug("Creating Packt Camel Component");
    }

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        LOGGER.debug("Creating Packt Camel Endpoint");
        PacktEndpoint packtEndpoint = new PacktEndpoint(uri, this);
        setProperties(packtEndpoint, parameters);
        return packtEndpoint;
    }

}
