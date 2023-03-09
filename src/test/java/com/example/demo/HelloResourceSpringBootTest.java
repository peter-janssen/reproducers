package com.example.demo;

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

    @Test
    void responseBodyTruncated() {
        final ResponseEntity<String> response = testRestTemplate.getForEntity("/fail", String.class);
        final String expectedResponseBody = "<!doctype html><html lang=\"en\"><head><title>HTTP Status 500 – Internal Server Error</title><style type=\"text/css\">body {font-family:Tahoma,Arial,sans-serif;} h1, h2, h3, b {color:white;background-color:#525D76;} h1 {font-size:22px;} h2 {font-size:16px;} h3 {font-size:14px;} p {font-size:12px;} a {color:black;} .line {height:1px;background-color:#525D76;border:none;}</style></head><body><h1>HTTP Status 500 – Internal Server Error</h1></body></html>";
        assertAll(
            () -> assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode()),
            () -> assertEquals(455L, response.getHeaders().getContentLength()),
            () -> assertEquals(expectedResponseBody, response.getBody())
        );
    }
}