package br.com.fiap.pontoeletronicoconsultaregistro.core.application;

import br.com.fiap.pontoeletronicoconsultaregistro.core.application.service.AutenticacaoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
@Component
public class AutenticacaoSuccessHandler implements AuthenticationSuccessHandler {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoSuccessHandler(AutenticacaoService autenticacaoService){
        this.autenticacaoService = autenticacaoService;
    }
    @Override
    public void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            DefaultOidcUser defaultOidcUser = (DefaultOidcUser) authentication.getPrincipal();
            Map<String, Object> userAttributes = defaultOidcUser.getAttributes();
            //this.autenticacaoService.autenticarUsuario(authentication.getName(), true);
            System.out.println("user attribute; " + userAttributes.get("cognito:username"));
            System.out.println("user 2; " + authentication.getName());
            response.sendRedirect("/");
        }
    }
}
