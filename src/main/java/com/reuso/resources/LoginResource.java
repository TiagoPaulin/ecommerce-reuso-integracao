package com.reuso.resources;

import com.reuso.entities.PessoaFisica;
import com.reuso.entities.abstracts.Usuario;
import com.reuso.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/login")
public class LoginResource {

    @Autowired
    LoginService loginService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> userCredentials) {

        String email = userCredentials.get("email");
        String senha = userCredentials.get("senha");

        Map<String, Object> response = new HashMap<>();

        Long userId = loginService.verificarLogin(email, senha);

        if (userId != null) {
            response.put("success", true);
            response.put("message", "Login realizado com sucesso!");
            response.put("userId", userId);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Credenciais inválidas.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

    }

}
