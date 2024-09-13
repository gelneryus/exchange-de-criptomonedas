package Mindhub.RaspCash.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()

               //  .antMatchers("/rest/**").permitAll()
              //  .antMatchers("/h2-console/**").

                //Permisos para el ADMIN
                .antMatchers("/rest/**").hasAuthority("ADMIN")
                .antMatchers("/h2-console/**").hasAuthority("ADMIN")

                //Permisos para el CLIENT
                .antMatchers("/web/carrito.html").hasAuthority("USER")
                .antMatchers("/web/Crypto.html").hasAuthority("USER")
                .antMatchers("/web/DeFi.html").hasAuthority("USER")
                .antMatchers("/web/nft.html").hasAuthority("USER")
                .antMatchers("/web/merchandising.html").hasAuthority("USER")
                .antMatchers("/web/usuario.html").hasAuthority("USER")


                .antMatchers(HttpMethod.POST, "/api/usuarios","/api/**").permitAll()

                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers("/web/**").permitAll()

                .antMatchers("/web/**").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/Terminos.html").permitAll()
                .antMatchers("/preguntas.html").permitAll()
                .antMatchers("/PoliticaPrivacidad.html").permitAll()
                .antMatchers("/Empleos.html").permitAll();

        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login").permitAll();

        http.logout().logoutUrl("/api/logout");

        http.csrf().disable();

        //disabling frameOptions so h2-console can be accessed

        http.headers().frameOptions().disable();

        // if user is not authenticated, just send an authentication failure response

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }


}
