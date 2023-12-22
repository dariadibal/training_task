package org.home.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    private static PropertiesManager instance;

    private final ThreadLocal<Properties> props = new ThreadLocal<>();

    private PropertiesManager() {}

    public Properties getProperties() throws IOException {
        if (this.props.get() == null) {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try(InputStream resourceStream = loader.getResourceAsStream("properties.properties")) {
                this.props.set(new Properties());
                this.props.get().load(resourceStream);
            }
        }
        return this.props.get();
    }

    public static PropertiesManager getInstance() {
        if (instance == null) {
            instance = new PropertiesManager();
        }
        return instance;
    }
}
