package com.example.demo;

import org.glassfish.jersey.server.ContainerResponse;
import org.glassfish.jersey.servlet.internal.ResponseWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloResourceSpringBootTest {

    @Autowired
    public TestRestTemplate testRestTemplate;

    /**
     * This test succeeds when the following code change is made:
     * <p/>
     * {@link ResponseWriter#writeResponseStatusAndHeaders(long, ContainerResponse)}
     *
     * <pre>
     *     {@code
     *          // modification of the response headers will have no effect
     *          // after the invocation of sendError.
     * -        final MultivaluedMap<String, String> headers = getResponseContext().getStringHeaders();
     * +        final MultivaluedMap<String, String> headers = responseContext.getStringHeaders();
     *          for (final Map.Entry<String, List<String>> e : headers.entrySet()) }{
     * </pre>
     * <p/>
     */
    @Test
    void responseBodyTruncated() {
        final ResponseEntity<String> response = testRestTemplate.getForEntity("/fail", String.class);
        final String expectedResponseBody = "An exception mapping did not successfully produce and processed a response. Logging the exception propagated to the default exception mapper.";
        assertAll(
                () -> assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode()),
                () -> assertEquals(141L, response.getHeaders().getContentLength()),
                () -> assertEquals(expectedResponseBody, response.getBody())
        );
    }
}