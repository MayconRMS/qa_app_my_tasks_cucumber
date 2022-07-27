package br.com.mrms.mytasks.service;

import io.restassured.response.ValidatableResponse;

public interface RestPort {

    ValidatableResponse getAuth();

    ValidatableResponse getUsers();

    ValidatableResponse getTasks();

    ValidatableResponse getCategory();

    ValidatableResponse updateStatusTasks();

}
