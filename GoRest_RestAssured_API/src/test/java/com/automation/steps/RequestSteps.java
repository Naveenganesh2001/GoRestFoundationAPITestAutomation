package com.automation.steps;


import com.automation.pojo.CreateUserRequestPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;

public class RequestSteps {
    @Given("user wants to call {string} end point")
    public void user_wants_to_call_end_point(String endPoint) {
        RestAssuredUtils.setEndPoint(endPoint);
    }

    @Given("set header {string} to {string}")
    public void set_header_to(String key, String value) {

        if (value.contains("@token.value")) {
            value = value.replace("@token.value", ConfigReader.getProperty("token.value"));
        }
        RestAssuredUtils.setHeader(key, value);
    }

    @Given("set request body from file {string}")
    public void set_request_body_from_file(String filePath) throws FileNotFoundException, JsonProcessingException {
        String content = RestAssuredUtils.getDataFromJsonFile(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        CreateUserRequestPojo requestPojo = objectMapper.readValue(content, CreateUserRequestPojo.class);
        RestAssuredUtils.setBody(requestPojo);
        ConfigReader.setObject("request_pojo", requestPojo);

    }

    @When("user performs post call")
    public void user_performs_post_call() {
        RestAssuredUtils.post();
    }

    @When("user performs get call")
    public void user_performs_get_call() {
        RestAssuredUtils.get();
    }


    @When("user performs put call")
    public void userPerformsPutCall() {
        RestAssuredUtils.put();
    }


}



