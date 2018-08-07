package example.service01;

import example.Service01Tests;
import example.category.RegressionCat;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;


import org.junit.Test;
import org.junit.Assert;
import org.junit.experimental.categories.Category;

public class CreateUser {

    @Test
    @Category({RegressionCat.class})
    public void postRequestCreateUser() throws JSONException {

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Robocop");
        requestParams.put("job", "Police officer");

        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toString());
        Response response = httpRequest.post("/api/users");
        JsonPath jsonPathEvaluator = response.jsonPath();

        int statusCode = response.getStatusCode();
        String name = jsonPathEvaluator.get("name");
        String job = jsonPathEvaluator.get("job");


        Assert.assertEquals(statusCode, 201);
        Assert.assertEquals("Alex J. Murphy", name);
        Assert.assertEquals("Police officer", job);

    }
}
