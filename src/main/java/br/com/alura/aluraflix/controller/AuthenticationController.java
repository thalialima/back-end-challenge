package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.config.security.TokenService;
import br.com.alura.aluraflix.controller.dto.TokenDTO;
import br.com.alura.aluraflix.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
//contém a lógica de autenticação
public class AuthenticationController {

    //não vem configurada para injeção de dependência
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid LoginForm loginForm){
        UsernamePasswordAuthenticationToken dataLogin = loginForm.toUsernamePasswordAuthenticationToken();

        try{
            //método que faz a autenticação
            Authentication authentication = authenticationManager.authenticate(dataLogin);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }

    }

}








