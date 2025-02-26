package api;

import helpers.enums.HttpStatus;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static io.restassured.RestAssured.given;

public class RestClient {
    public static final Logger logger = LoggerFactory.getLogger(RestClient.class);
    protected RequestSpecification requestSpec = new RequestSpecBuilder().build();

    RestClient(String baseUri) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = "";
        if (RestAssured.filters().isEmpty()) {
            RestAssured.filters(new AllureRestAssured());
        }
        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())
                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig().defaultObjectMapperType(ObjectMapperType.GSON));
        RestAssured.useRelaxedHTTPSValidation();
    }


    /**
     * Universal method for common request.
     *
     * @param method      HTTP method.
     * @param statusCode  HTTP status code.
     * @param baseRequest request params {@link BaseRequest BaseRequest.class}
     */
    @Step("{method} request with params {params}, response should be {statusCode}.")
    <T> ValidatableResponse generalRequest(Method method, HttpStatus statusCode, BaseRequest<T> baseRequest) {
        RequestSpecification res = given().spec(requestSpec);
        Map<String, Object> params = baseRequest.params() == null ? new TreeMap<>() : baseRequest.params();
        Object body = baseRequest.body();
        String path = baseRequest.path() == null ? "" : baseRequest.path();
        res.queryParams(params);
        if (body != null) res.body(body);
        return res.when().log().all().request(method, path).then().log().all().statusCode(statusCode.getCode());
    }

    /**
     * Like [generalRequest], but returns deserialized body.
     *
     * @param method      HTTP method.
     * @param statusCode  HTTP status code.
     * @param baseRequest request params {@link BaseRequest BaseRequest.class}
     * @return already deserialized body.
     */
    public <T> T request(Method method, HttpStatus statusCode, BaseRequest<T> baseRequest) {
        ValidatableResponse response = generalRequest(method, statusCode, baseRequest);
        String resultString = response.extract().asString();
        if (!resultString.isEmpty()) {
            logger.debug("Received JSON: {}", resultString);
            return response.extract().as(baseRequest.responseClass());
        } else return null;
    }

    /**
     * Method for `GET` http request.
     *
     * @param statusCode  HTTP status code.
     * @param baseRequest request params {@link BaseRequest BaseRequest.class}
     * @param <T>         class for deserialization
     * @return deserialized body.
     */
    @Step("GET request with params {params}, response should be {statusCode}.")
    public <T> T get(HttpStatus statusCode, BaseRequest<T> baseRequest) {
        return request(Method.GET, statusCode, baseRequest);
    }

    /**
     * Method for `POST` http request.
     *
     * @param statusCode  HTTP status code.
     * @param baseRequest request params {@link BaseRequest BaseRequest.class}
     * @param <T>         class for deserialization
     * @return deserialized body.
     */
    @Step("POST request with params {params}, response should be {statusCode}.")
    public <T> T post(HttpStatus statusCode, BaseRequest<T> baseRequest) {
        return request(Method.POST, statusCode, baseRequest);
    }

    /**
     * Method for `PUT` http request.
     *
     * @param statusCode  HTTP status code.
     * @param baseRequest request params {@link BaseRequest BaseRequest.class}
     * @param <T>         class for deserialization
     * @return deserialized body.
     */
    @Step("PUT request with params {params}, response should be {statusCode}.")
    public <T> T put(HttpStatus statusCode, BaseRequest<T> baseRequest) {
        return request(Method.PUT, statusCode, baseRequest);
    }

    /**
     * Method for `PATCH` http request.
     *
     * @param statusCode  HTTP status code.
     * @param baseRequest request params {@link BaseRequest BaseRequest.class}
     * @param <T>         class for deserialization
     * @return deserialized body.
     */
    @Step("PATCH request with params {params}, response should be {statusCode}.")
    public <T> T patch(HttpStatus statusCode, BaseRequest<T> baseRequest) {
        return request(Method.PATCH, statusCode, baseRequest);
    }

    /**
     * Method for `DELETE` http request.
     *
     * @param statusCode  HTTP status code.
     * @param baseRequest request params {@link BaseRequest BaseRequest.class}
     * @param <T>         class for deserialization
     * @return deserialized body.
     */
    @Step("DELETE request with params {params}, response should be {statusCode}.")
    public <T> T delete(HttpStatus statusCode, BaseRequest<T> baseRequest) {
        return request(Method.DELETE, statusCode, baseRequest);
    }

    /**
     * Like [generalRequest], but without status code checks
     *
     * @param method      HTTP method.
     * @param baseRequest request params {@link BaseRequest BaseRequest.class}
     */
    @Step("{method} request with params {params}")
    <T> ValidatableResponse generalRequestWithoutStatus(Method method, BaseRequest<T> baseRequest) {
        RequestSpecification res = given().spec(requestSpec);
        Map<String, Object> params = baseRequest.params() == null ? new TreeMap<>() : baseRequest.params();
        Object body = baseRequest.body();
        String path = baseRequest.path() == null ? "" : baseRequest.path();
        res.queryParams(params);
        if (body != null) res.body(body);
        return res.when().log().all().request(method, path).then().log().all();
    }

    /**
     * Like [generalRequest], but used when response is list.
     *
     * @param method      HTTP method.
     * @param statusCode  HTTP status code.
     * @param baseRequest request params {@link BaseRequest BaseRequest.class}
     *
     * @return already deserialized list from body.
     */
    public <T> List<T> requestList(Method method, HttpStatus statusCode, BaseRequest<T> baseRequest) {
        ValidatableResponse response = generalRequest(method, statusCode, baseRequest);
        String resultString = response.extract().asString();
        if (!resultString.isEmpty()) {
            logger.debug("Received JSON List: {}", resultString);
            return response.extract().body().jsonPath().getList(".", baseRequest.responseClass());
        } else return null;
    }
}
