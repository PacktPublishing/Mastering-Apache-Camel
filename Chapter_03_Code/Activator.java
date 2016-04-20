package com.packt.camel.chapter3;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private CamelContext camelContext;
    private ServiceRegistration serviceRegistration;

    public void start(BundleContext bundleContext) throws Exception {
        camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new MyRouteBuilder());
        serviceRegistration = bundleContext.registerService(CamelContext.class, camelContext, null);
        camelContext.start();
    }

    public void stop(BundleContext bundleContext) throws Exception {
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
        if (camelContext != null) {
            camelContext.stop();
        }
    }

}
