package com.imindersingh.steps;

import com.google.gson.Gson;
import com.imindersingh.dtos.PostBody;
import com.imindersingh.http.ReferenceApiRequests;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStore;
import kong.unirest.HttpResponse;
import org.json.JSONObject;

import static org.assertj.core.api.Assertions.assertThat;


public class ReferenceApiSteps {

    private DataStore dataStore = new DataStore();
    private ReferenceApiRequests request = new ReferenceApiRequests();

    @Step("GET request to <endpoint> endpoint")
    public void getRequest(final String endpoint) {
        HttpResponse response = request.get(endpoint);
        dataStore.put("response", response);
    }

    @Step("POST request to <endpoint> endpoint with body <body>")
    public void postRequest(final String endpoint, final String body) {
        PostBody postBody = new PostBody();
        postBody.setTest(body);
        String requestBody = new Gson().toJson(postBody);
        dataStore.put("body", postBody.getTest());
        HttpResponse response = request.post(endpoint, requestBody);
        dataStore.put("response", response);
    }

    @Step("POST request to <endpoint> endpoint")
    public void postRequest(final String endpoint) {
        PostBody postBody = new PostBody();
        postBody.setTest("test1234");
        String body = new Gson().toJson(postBody);
        HttpResponse response = request.post(endpoint, body);
        dataStore.put("response", response);
    }

    @Step("Assert the http response code is <httpResponseCode>")
    public void assertHttpResponseCode(final String httpResponseCode) {
        HttpResponse response = (HttpResponse) dataStore.get("response");
        assertThat(response.getStatus()).isEqualTo(Integer.parseInt(httpResponseCode));
    }

    @Step("Assert the http response text is <httpResponseText>")
    public void assertHttpResponseText(final String httpResponseText) {
        HttpResponse response = (HttpResponse) dataStore.get("response");
        assertThat(response.getStatusText()).isEqualTo(httpResponseText);
    }

    @Step("Assert the http response body contains the posted value")
    public void assertHttpResponseBody() {
        HttpResponse response = (HttpResponse) dataStore.get("response");
        JSONObject jsonObject = new JSONObject(response.getBody().toString());
        String responseBody = jsonObject.getJSONArray("receivedRequest").getJSONObject(0).get("test").toString();
//        Gson gson = new Gson();
//        User user = gson.fromJson(json_string, User.class);
        assertThat(responseBody).isEqualTo((String) dataStore.get("body"));
    }
}
