package test.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class App {
    @Autowired
    ObjectMapper objectMapper;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    public void ObjectMapperBean() {
        log.info("""
                Found and registered modules by plain ObjectMapper:
                %s""".formatted(new ObjectMapper().findAndRegisterModules().getRegisteredModuleIds()));
        log.info("""
                Found and registered modules by Spring ObjectMapper Bean:
                %s""".formatted(objectMapper.getRegisteredModuleIds()));
    }
}

