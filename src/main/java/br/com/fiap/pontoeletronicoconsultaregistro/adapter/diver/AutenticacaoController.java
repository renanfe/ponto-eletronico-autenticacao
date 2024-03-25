package br.com.fiap.pontoeletronicoconsultaregistro.adapter.diver;

import br.com.fiap.pontoeletronicoconsultaregistro.core.application.service.AutenticacaoService;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService){
        this.autenticacaoService = autenticacaoService;
    }
    @GetMapping("/")
    public String buscarRegistroPontoPorFuncionario(){
        return "Autenticado com sucesso";
    }

}
