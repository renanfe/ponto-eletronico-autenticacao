package br.com.fiap.pontoeletronicoconsultaregistro.core.application.service;

import br.com.fiap.pontoeletronicoconsultaregistro.adapter.diven.infra.UserRepository;
import br.com.fiap.pontoeletronicoconsultaregistro.adapter.diven.infra.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AutenticacaoService {

    private final UserRepository userRepository;

    public AutenticacaoService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void autenticarUsuario (String username, Boolean isLogged){
        UserEntity userEntity = UserEntity.builder()
                .isLogged(isLogged)
                .username(username)
                .build();
        this.userRepository.save(userEntity);
    }
    public Optional<UserEntity> isUsuarioAutenticado (String username) {
        return this.userRepository.findByUsername(username);
    }
}
