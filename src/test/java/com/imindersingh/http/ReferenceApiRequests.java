package com.imindersingh.http;

import com.github.sitture.env.config.EnvConfig;
import kong.unirest.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class ReferenceApiRequests extends Requests {

    private final String ACCEPT = "Accept";
    private final String CONTENT_TYPE  = "Content-Type";
    private final String ACCEPT_DEFAULT = "*/*";
    private final String APPLICATION_VND_JSON = "application/vnd.json";

    public ReferenceApiRequests() {
        super(EnvConfig.get("HOST"));
    }

    public HttpResponse get(String path) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(ACCEPT, "*/*");
        return getRequest(path, headers);
    }

    public HttpResponse get(String path, Map<String, String> headers) {
        return getRequest(path, headers);
    }

    public HttpResponse post(String path, String body) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(ACCEPT, ACCEPT_DEFAULT);
        headers.put(CONTENT_TYPE, APPLICATION_VND_JSON);
        return postRequest(path, headers, body);
    }

    public HttpResponse post(String path, Map<String, String> headers, String body) {
        return postRequest(path, headers, body);
    }

}
