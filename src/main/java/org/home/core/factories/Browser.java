package org.home.core.factories;

import org.openqa.selenium.remote.AbstractDriverOptions;

public interface Browser {
    public AbstractDriverOptions<?> getOptions();
}
