package br.com.alura.aluraflix.config.security;

import br.com.alura.aluraflix.model.User;
import br.com.alura.aluraflix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//O controller de autencicação pertence ao Spring, e ele que vai chamar essa classe
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException("Credenciais Inválidas");
    }




}



