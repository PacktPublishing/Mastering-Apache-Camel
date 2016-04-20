package com.packt.camel.chapter5r;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyAggregator implements AggregationStrategy {

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        if (oldExchange == null) {
            return newExchange;
        }

        String persons = oldExchange.getIn().getBody(String.class);
        String newPerson = newExchange.getIn().getBody(String.class);

        // put orders together separating by semi colon
        persons = persons + newPerson;
        oldExchange.getIn().setBody(persons);

        return oldExchange;
    }

}
