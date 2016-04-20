package com.packt.camel.chapter4c;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.CompositeRegistry;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.util.jndi.JndiContext;

public final class Main {

    public static void main(String[] args) throws Exception {
        SimpleRegistry simpleRegistry = new SimpleRegistry();
        simpleRegistry.put("simpleBean", new SimpleBean());
        JndiRegistry jndiRegistry = new JndiRegistry(new JndiContext());
        jndiRegistry.bind("otherBean", new SimpleBean());
        CompositeRegistry registry = new CompositeRegistry();
        registry.addRegistry(simpleRegistry);
        registry.addRegistry(jndiRegistry);

        CamelContext camelContext = new DefaultCamelContext(registry);
        camelContext.addRoutes(new RouteBuilder() {
                                   @Override
                                   public void configure() throws Exception {
                                        from("direct:start").to("bean:simpleBean").to("mock:stop");
                                       from("direct:other").to("bean:otherBean").to("mock:stop");
                                   }
                               }
        );

        camelContext.start();

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start", "Packt");
        producerTemplate.sendBody("direct:other", "Other");

        camelContext.stop();
    }

}
