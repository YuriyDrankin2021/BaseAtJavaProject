package api;

import data.dto.ToDoMockDTO;
import helpers.enums.HttpStatus;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static helpers.TestConfig.config;

public class MockRestClient extends RestClient {
    String todos = "/todos";
    RequestSpecification requestSpecificationJson = new RequestSpecBuilder()
            .removeParam("Content-Type")
            .setContentType("application/json")
            .build();

    public MockRestClient() {
        super(config().getProperty("mockUri"));
        requestSpec.spec(requestSpecificationJson);
    }

    @Step("Get todos data from mock")
    public List<ToDoMockDTO> getMockTodos() {
        BaseRequest<ToDoMockDTO> request = new BaseRequest<>();
        request.path(todos);
        request.responseClass(ToDoMockDTO.class);
        return requestList(Method.GET, HttpStatus.OK, request);
    }


}
