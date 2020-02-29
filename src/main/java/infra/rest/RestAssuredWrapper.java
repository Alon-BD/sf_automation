package infra.rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.specification.RequestSpecification;

public class RestAssuredWrapper {

    public RestAssuredWrapper() {
        initializeRestAssured();
    }

    public RequestSpecification ra() {
        return RestAssured.given();
    }

    private void initializeRestAssured() {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.defaultParser = Parser.JSON;
    }
}
