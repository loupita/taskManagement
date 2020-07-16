package org.sid.jwtspringsec.security;

/*
    @Param

    Classe pour gerer les constantes nécessaires pour la sécurité avec JWT
 */
public class SecurityConstants {
    public static final String SECRET = "jos@gmail.com";
    public static final long  EXPIRATION_DATE=864_000_000 ; //10 jours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
