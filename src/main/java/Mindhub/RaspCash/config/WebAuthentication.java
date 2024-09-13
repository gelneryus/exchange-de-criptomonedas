package Mindhub.RaspCash.config;

import Mindhub.RaspCash.models.Usuario;
import Mindhub.RaspCash.respositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName -> {
            Usuario usuario = usuarioRepositorio.findByEmail(inputName);

            if (usuario != null){
                if (usuario.getEmail().equals("admin")) {
                    return new User(usuario.getEmail(),usuario.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
                }else{
                    return new User(usuario.getEmail(),usuario.getPassword(), AuthorityUtils.createAuthorityList("USER"));
                }
            }else {
                throw new UsernameNotFoundException("Usuario desconocido: " + inputName);
            }
        });
    }


}
