package br.com.mrms.mytasks.controller.response;

import br.com.mrms.mytasks.model.ERole;
import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String username;
    private List<String> roles;

    public JwtResponse(String token, Integer id, String username, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public boolean isAdmin() {
        return roles.contains(ERole.ROLE_ADMIN.name());
    }
}
