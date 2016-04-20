package com.packt.camel.chapter4b;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.util.jndi.JndiContext;

public final class Main {

    public static void main(String[] args) throws Exception {
        JndiRegistry registry = new JndiRegistry(new JndiContext());
        registry.bind("simpleBean", new SimpleBean());

        CamelContext camelContext = new DefaultCamelContext(registry);
        camelContext.addRoutes(new RouteBuilder() {
                                   @Override
                                   public void configure() throws Exception {
                                        from("direct:start").to("bean:simpleBean").to("mock:stop");
                                   }
                               }
        );

        camelContext.start();

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start", "Packt");

        camelContext.stop();
    }

}
