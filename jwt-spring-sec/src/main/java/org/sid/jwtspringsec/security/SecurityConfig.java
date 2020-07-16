package org.sid.jwtspringsec.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
    Gestion de la sécurité avec Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService userDetailsService; //Système d'authentification basé sur une couche service,
    // il faut creerune classe pour implementer cette interface
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /* 1 er méthode pour gérer l'auth//Pour stocker les utilisateurs en mémoire
        auth.inMemoryAuthentication().
                withUser("admin").password("{noop}1234").roles("ADMIN", "USER")
        .and()
        .withUser("user").password("{noop}1234").roles("USER");
        */
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(bCryptPasswordEncoder);
    }

    //Méthode permettant de définir les droits d'accès
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //désactive le synchronise token generer par spring security
        http.csrf().disable();
        //Desactive l'authenification basé sur les sessions utile pour utiliser JWT,
        // dire à spring d'utiliser une securité basée sur le mode stateless
        // Formulaire générer par string
        // http.formLogin();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/users/**", "/login/**")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/tasks/**").hasAuthority("ADMIN");
        //Indique que toutes les ressources de l'app nécessite une authentification

        http.authorizeRequests().antMatchers("/login/**", "/register/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/tasks/**").hasAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();

        //Ajoute le filtre cree pour jwt
       http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        //necessaire pour appeler les end points
       http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
