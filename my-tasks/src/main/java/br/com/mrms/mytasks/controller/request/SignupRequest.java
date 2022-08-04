package br.com.mrms.mytasks.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private Set<String> roles;
}
