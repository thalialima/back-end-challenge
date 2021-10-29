package br.com.alura.aluraflix.config.security;

import br.com.alura.aluraflix.model.User;
import br.com.alura.aluraflix.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Filtro chamado uma única vez a cada requisição
public class AuthenticationByTokenFilter extends OncePerRequestFilter {

    //não é possível fazer injeção de dependências aqui
    private TokenService tokenService;

    private UserRepository userRepository;

    public AuthenticationByTokenFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperateToken(request);

        boolean valid = tokenService.isTokenValid(token);

        if(valid){
            authenticateClient(token);
        }
        System.out.println(valid);
        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token){

        Long userId = tokenService.getIdUser(token);
        User user = userRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperateToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
