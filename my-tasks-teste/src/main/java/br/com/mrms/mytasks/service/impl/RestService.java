package br.com.mrms.mytasks.service.impl;

import br.com.mrms.mytasks.service.RestPort;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
public class RestService implements RestPort {

    @Value("${api.basic.auth.userName}")
    private String username;

    @Value("${api.basic.auth.pass}")
    private String password;

    @Value("${api.basic.taskUrl}")
    private String taskUrl;

    @Override
    public ValidatableResponse getAuth() {
        return null;
    }

    @Override
    public ValidatableResponse getUsers() {
        return null;
    }

    @Override
    public ValidatableResponse getTasks() {
        String authInfo = username + ":" + password;
        String basicAuth = Base64.encodeBase64String(authInfo.getBytes());

        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .header("Authorization" , "Basic " + basicAuth)
                .when()
                .get(taskUrl)
                .then();
        System.out.println("Retorno API {}:" + response.extract().response().asString());
        return response;
    }

    @Override
    public ValidatableResponse getCategory() {
        return null;
    }

    @Override
    public ValidatableResponse updateStatusTasks() {
        return null;
    }
}
