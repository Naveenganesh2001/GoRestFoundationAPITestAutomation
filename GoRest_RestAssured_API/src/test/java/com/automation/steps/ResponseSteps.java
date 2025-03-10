package com.automation.steps;



import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class ResponseSteps {
    @Then("verify status code {int}")
    public void verify_status_code(int statusCode) {
        Assert.assertEquals(RestAssuredUtils.getStatusCode(), statusCode);
    }


    @And("store {string} into {string}")
    public void storeInto(String jsonPath, String configKey) {
        String value = RestAssuredUtils.getResponseFieldValue(jsonPath);
        ConfigReader.setProperty(configKey, value);
    }


}
