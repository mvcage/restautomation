package example.service01;

import example.Service01Tests;
import example.category.MainTestClass;
import example.category.RegressionCat;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class GetUser extends MainTestClass {

    @Test
    @Category({RegressionCat.class})
    public void getRequestSingleUser() throws JSONException {
        RequestSpecification httpRequest = RestAssured.given();
        RestAssured.baseURI = "https://reqres.in/";
        Response response = httpRequest.get("/api/users/2");

        JsonPath jsonPathEvaluator = response.jsonPath();
        int statusCode = response.getStatusCode();
        int id = jsonPathEvaluator.get("data.id");
        String firstName = jsonPathEvaluator.get("data.first_name");
        String lastName = jsonPathEvaluator.get("data.last_name");
        String avatar = jsonPathEvaluator.get("data.avatar");
        //System.out.println(response.body().asString());

        Assert.assertEquals("Correct status code returned", 200, statusCode);
        Assert.assertEquals("id is correct", 2, id);
        Assert.assertEquals("first_name is correct", "Janet", firstName);
        Assert.assertEquals("last_name is correct", "Weaver", lastName);
        Assert.assertEquals("avatar url is correct", "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg", avatar);

    }

    @Test
    public void getRequestSingleUserNotFound() throws JSONException {
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/users/808");
        JsonPath jsonPathEvaluator = response.jsonPath();
        int statusCode = response.getStatusCode();
        String body = response.body().asString();

        Assert.assertEquals("Correct error code returned", 404, statusCode);
        Assert.assertEquals("Correct status code returned", "{}", body);
    }
}
