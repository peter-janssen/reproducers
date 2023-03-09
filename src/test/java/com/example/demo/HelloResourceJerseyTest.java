package com.example.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * To run this test exclude jersey-spring6 (modify classpath in Intellij run configuration)
 */
public class HelloResourceJerseyTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(HelloResource.class).property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }

    @Test
    void responseBodyTruncated() {
        Response response = target("/fail").request().get();
        final String expectedResponseBody = "An exception mapping did not successfully produce and processed a response. Logging the exception propagated to the default exception mapper.";
        assertAll(
            () -> assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus()),
            () -> assertEquals("141", response.getHeaderString(HttpHeaders.CONTENT_LENGTH)),
            () -> assertEquals(expectedResponseBody, response.readEntity(String.class))
        );
    }
}
