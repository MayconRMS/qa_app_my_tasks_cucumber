package br.com.mrms.mytasks.steps;

import br.com.mrms.mytasks.service.RestPort;
import br.com.mrms.mytasks.service.impl.RestService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.ValidatableResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class ListarTarefasSteps {
    @Autowired
    private RestPort restPort;
    ValidatableResponse response;
    @Dado("^usuario logado$")
    public void usuarioLogado() {
        System.out.println("Logado");
        response = restPort.getTasks();
    }

    @Quando("retorno da api for {int}")
    public void retornoDaApiForSTATUS_CODE(Integer statusCode) {
        System.out.println("Status" + statusCode);
        assertTrue(response.extract().statusCode() == statusCode);
    }

    @Entao("quantidade de tarefas for {int}")
    public void quantidadeDeTarefasForQUANTIDADES(Integer quantidade) {
        System.out.println("Quantidade" + quantidade);
        String responseBody = response.extract().response().asString();
        assertTrue(!Objects.equals(responseBody, ""));
    }

}

