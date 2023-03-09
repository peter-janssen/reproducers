package com.example.demo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.springframework.stereotype.Service;

@Service
@Path("/")
public class HelloResource {

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello() {
        return "Hello from Spring";
    }

    @GET
    @Path("/fail")
    @Produces("text/plain")
    public String fail() {
        throw new RuntimeException("tada");
    }
}