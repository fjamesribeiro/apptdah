package br.com.zoi.apptdah.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @GetMapping("/callback")
    public ResponseEntity<?> authCallback(@RequestParam("access_token") String accessToken) {
        // Aqui você pode salvar o token em um cookie ou redirecionar para o frontend
    	System.out.println("Token recebido: " + accessToken); 	
        return ResponseEntity.ok(Map.of("jwt", accessToken));
    }
}
