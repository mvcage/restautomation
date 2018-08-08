package example.service01;


import example.category.MainTestClass;
import example.category.RegressionCat;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;


import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.experimental.categories.Category;
import org.junit.AfterClass;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class CreateUser extends MainTestClass {

    /*private static StringBuilder builder = new StringBuilder();
    @AfterClass
    public static void afterClass() throws IOException {
        PrintWriter logFile = new PrintWriter("C:\\Program Files (x86)\\Jenkins\\jobs\\AutomationREST\\TestResult.xml", "UTF-8");
        logFile.write(builder.toString());
        logFile.close();
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            if (description != null) {
                builder.append(description);
            }
            if (e != null) {
                builder.append(' ');
                builder.append(e);
            }
            builder.append(" FAIL\n");
        }

        @Override
        protected void succeeded(Description description) {
            if (description != null) {
                builder.append(description);
            }
            builder.append(" OK\n");
        }
    };*/

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
        Assert.assertEquals("Police officerr", job);

    }
}
