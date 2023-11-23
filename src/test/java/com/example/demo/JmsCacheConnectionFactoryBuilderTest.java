package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;

import jakarta.jms.ConnectionFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class JmsCacheConnectionFactoryBuilderTest {

    @Test
    void testCreateCacheConnectionFactory(@Mock final ConnectionFactory connectionFactory) {

        UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
        adapter.setTargetConnectionFactory(connectionFactory);
        adapter.setUsername("UserName");
        adapter.setPassword("Password");
        adapter.afterPropertiesSet();

        final CachingConnectionFactory cacheConnectionFactory = new CachingConnectionFactory(adapter);
        assertNotNull(cacheConnectionFactory);
    }

}