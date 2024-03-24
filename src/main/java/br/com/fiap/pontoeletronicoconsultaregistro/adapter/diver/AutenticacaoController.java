package br.com.fiap.pontoeletronicoconsultaregistro.adapter.diver;

import br.com.fiap.pontoeletronicoconsultaregistro.adapter.diven.infra.entity.UserEntity;
import br.com.fiap.pontoeletronicoconsultaregistro.core.application.service.AutenticacaoService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/v1")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService){
        this.autenticacaoService = autenticacaoService;
    }
    @GetMapping("/")
    public String buscarRegistroPontoPorFuncionario(){
        return "Autenticado com sucesso";
    }

    @GetMapping("/isAuthenticado/{username}")
    public ResponseEntity<UserEntity> isUsuarioAutenticado(@PathVariable String username){
        return autenticacaoService.isUsuarioAutenticado(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
