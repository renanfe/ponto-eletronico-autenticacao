package br.com.fiap.pontoeletronicoconsultaregistro.adapter.diven.infra;

import br.com.fiap.pontoeletronicoconsultaregistro.adapter.diven.infra.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
}
