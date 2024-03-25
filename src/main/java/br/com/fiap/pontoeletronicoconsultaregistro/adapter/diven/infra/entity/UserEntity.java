package br.com.fiap.pontoeletronicoconsultaregistro.adapter.diven.infra.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Builder
@Getter
@RedisHash("User")
public class UserEntity {

    @Id
    private String username;
    public boolean isLogged;

}
