package com.latif.userservice.utils;

import java.util.Locale;

public final class ProjectConstants {
    public static final String DEFAULT_ENCODING = "UTF-8";

    public static final String PROJECT_BASE_PACKAGE = "com.latif.userservice";

    public static final Locale ID_LOCALE = new Locale.Builder().build();

    private ProjectConstants() {

        throw new UnsupportedOperationException();
    }
}
