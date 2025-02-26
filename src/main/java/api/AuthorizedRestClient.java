package api;

import data.AuthData;
import data.UserAuthData;
import helpers.enums.HttpStatus;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Properties;

import static helpers.TestConfig.config;

public class AuthorizedRestClient extends RestClient {
    Properties properties = config();
    private final String authUri = properties.getProperty("authUri");
    RequestSpecification requestSpecificationJson = new RequestSpecBuilder()
            .removeParam("Content-Type")
            .setContentType("application/json")
            .build();
    RequestSpecification requestSpecificationAuth = new RequestSpecBuilder()
            .setContentType("application/x-www-form-urlencoded")
            .build();

    AuthorizedRestClient(String baseUri, UserAuthData userAuthData) {
        super(baseUri);
        setAuthBearer(getAuthTokenData(userAuthData).getAccessToken());
        requestSpec.spec(requestSpecificationJson);
    }

    private AuthData getAuthTokenData(UserAuthData userAuthData) {
        BaseRequest<AuthData> request = new BaseRequest<>();
        requestSpec.spec(requestSpecificationAuth);
        requestSpec.baseUri(authUri);
        String body = "grant_type=password&client_id=clientId&userName=" + userAuthData.getUserName()
                + "&password=" + userAuthData.getUserPass();
        request.body(body);
        request.responseClass(AuthData.class);
        return post(HttpStatus.OK, request);
    }

    private void setAuthBearer(String bearer) {
        requestSpec.spec(new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + bearer)
                .build());
    }

}
