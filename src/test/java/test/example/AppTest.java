package test.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AppTest
{
    @Autowired
    ObjectMapper objectMapperBean;

    @Test
    void testObjectMapper()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        String[] expectedModules = {"com.fasterxml.jackson.module.kotlin.KotlinModule", "com.fasterxml.jackson.module.scala.DefaultScalaModule"};
        assertThat(objectMapper.getRegisteredModuleIds()).contains(expectedModules);
        assertThat(objectMapperBean.getRegisteredModuleIds()).contains(expectedModules);
    }
}
