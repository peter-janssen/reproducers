package com.example.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfiguration extends ResourceConfig{

    public JerseyConfiguration() {
        register(HelloResource.class);
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}
