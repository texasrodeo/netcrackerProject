package com.netcrackerTask.backend.init;

import org.springframework.boot.SpringApplication;

/**
 * Entry point for launch spring boot.
 *
 * @since 0.0.1
 */
public final class EntryPoint {
    /**
     * Private constructor.
     */
    private EntryPoint() {
    }

    /**
     * Entry point.
     *
     * @param args Arguments.
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    public static void main(final String[] args) {
        SpringApplication.run(SpringBootConfig.class, args);

    }
}
