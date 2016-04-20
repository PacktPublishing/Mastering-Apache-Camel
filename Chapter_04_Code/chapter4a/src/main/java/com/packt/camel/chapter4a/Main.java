package com.packt.camel.chapter4a;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public final class Main {

    public static void main(String[] args) throws Exception {
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("simpleBean", new SimpleBean());

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
