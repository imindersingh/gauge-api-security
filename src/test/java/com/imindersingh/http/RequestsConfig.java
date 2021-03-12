package com.imindersingh.http;

import com.github.sitture.env.config.EnvConfig;
import kong.unirest.Unirest;

public class RequestsConfig {

    static {
        if (EnvConfig.get("ZAP").equalsIgnoreCase("true")) {
            Unirest.config().proxy(EnvConfig.get("ZAP_PROXY_IP"), 8081);
        }
    }

}