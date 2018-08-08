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

public class GetUsersOnPage extends MainTestClass {

    @Test
    @Category({RegressionCat.class})
    public void getRequestListUsers() throws JSONException {
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/users?page=1");

        JsonPath jsonPathEvaluator = response.jsonPath();
        int statusCode = response.getStatusCode();
        int page = jsonPathEvaluator.get("page");
        int per_page = jsonPathEvaluator.get("per_page");
        int total = jsonPathEvaluator.get("total");
        int total_pages = jsonPathEvaluator.get("total_pages");
        int id01 = jsonPathEvaluator.get("data[0].id");
        String firstName01 = jsonPathEvaluator.get("data[0].first_name");
        String lastName01 = jsonPathEvaluator.get("data[0].last_name");
        String avatar01 = jsonPathEvaluator.get("data[0].avatar");


        Assert.assertEquals("Correct status code returned", 200, statusCode);
        Assert.assertEquals("page number is correct", 1, page);
        Assert.assertEquals("count of users on page is correct", 3, per_page);
        Assert.assertEquals("id is correct", 1, id01);
        Assert.assertEquals("first_name is correct", "George", firstName01);
        Assert.assertEquals("last_name is correct", "Bluth", lastName01);
        Assert.assertEquals("avatar url is correct", "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg", avatar01);
    }
}
