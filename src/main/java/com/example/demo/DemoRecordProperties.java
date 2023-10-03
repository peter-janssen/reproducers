package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;

import java.util.Map;

/**
 * @param test
 * @param hello custom description of field hello
 * @param map custom description of field map
 * @param hintedMap
 */
@ConfigurationProperties(prefix = "custom.record")
public record DemoRecordProperties(
        @DeprecatedConfigurationProperty(reason = "none of your business.", replacement = "custom.hello") @Deprecated String test,
        String hello,
        @Deprecated @DeprecatedConfigurationProperty(replacement = "custom.hinted-map") Map<String, NestedDemoProperties> map,
        Map<String, NestedDemoProperties> hintedMap) {
    /**
     * @param first first name
     * @param second last name
     */
    public record NestedDemoProperties(String first, String second) {
    }
}
