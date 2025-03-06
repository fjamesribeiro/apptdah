package br.com.zoi.apptdah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zoi.apptdah.dto.AuthResponseDto;
import br.com.zoi.apptdah.service.AuthenticationService;

@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {
	
	@Autowired
	private AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<AuthResponseDto> getJwtForGoogle(@AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(authenticationService.authenticate(principal));
    }
    
}
