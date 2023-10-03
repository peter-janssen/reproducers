package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;

import java.util.Map;
import java.util.Objects;


/**
 * DemoProperties
 */
@ConfigurationProperties(prefix = "custom")
public final class DemoProperties {
    /**
     * Field test
     *
     * @deprecated
     */
    @Deprecated
    private final String test;

    /**
     * Field hello
     */
    private final String hello;

    /**
     * Field map
     *
     * @deprecated
     */
    @Deprecated
    private final Map<String, NestedDemoProperties> map;

    /**
     * Field hintedMap
     */
    private final Map<String, NestedDemoProperties> hintedMap;


    /**
     * @param test      custom description of field test
     * @param hello     custom description of field hello
     * @param map       custom description of field map
     * @param hintedMap custom description of field hintedMap
     */
    public DemoProperties(
            @Deprecated String test,
            String hello,
            @Deprecated Map<String, NestedDemoProperties> map,
            Map<String, NestedDemoProperties> hintedMap) {
        this.test = test;
        this.hello = hello;
        this.map = map;
        this.hintedMap = hintedMap;
    }

    @DeprecatedConfigurationProperty(reason = "none of your business.", replacement = "custom.hello")
    @Deprecated
    public String test() {
        return test;
    }

    public String hello() {
        return hello;
    }

    @Deprecated
    @DeprecatedConfigurationProperty(replacement = "custom.hinted-map")
    public Map<String, NestedDemoProperties> map() {
        return map;
    }

    public Map<String, NestedDemoProperties> hintedMap() {
        return hintedMap;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==this) return true;
        if (obj==null || obj.getClass()!=this.getClass()) return false;
        var that = (DemoProperties) obj;
        return Objects.equals(this.test, that.test) &&
                Objects.equals(this.hello, that.hello) &&
                Objects.equals(this.map, that.map) &&
                Objects.equals(this.hintedMap, that.hintedMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(test, hello, map, hintedMap);
    }

    @Override
    public String toString() {
        return "DemoProperties[" +
                "test=" + test + ", " +
                "hello=" + hello + ", " +
                "map=" + map + ", " +
                "hintedMap=" + hintedMap + ']';
    }

    /**
     *
     */
    public static final class NestedDemoProperties {
        /**
         * first name
         */
        private final String first;
        /**
         * last name
         */
        private final String second;


        public NestedDemoProperties(String first, String second) {
            this.first = first;
            this.second = second;
        }

        public String first() {
            return first;
        }

        public String second() {
            return second;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj==this) return true;
            if (obj==null || obj.getClass()!=this.getClass()) return false;
            var that = (NestedDemoProperties) obj;
            return Objects.equals(this.first, that.first) &&
                    Objects.equals(this.second, that.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public String toString() {
            return "NestedDemoProperties[" +
                    "first=" + first + ", " +
                    "second=" + second + ']';
        }

    }
}
