package br.com.bossini.aula04_spring_mvc_boot.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.bossini.aula04_spring_mvc_boot.model.Cidade;
import br.com.bossini.aula04_spring_mvc_boot.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/cidades")
class CidadeResource {

    @Autowired
    private CidadeRepository cidadeRepositorio;

    @GetMapping("/listaTodas")
    public List<Cidade> todasCidades() {
        return cidadeRepositorio.findAll();
    }


    @GetMapping("/{letra}")
    public List<Cidade> peloNome(@PathVariable String letra) {
        return cidadeRepositorio.buscaPorNome(letra);
    }


    @GetMapping("/{latitude}/{longitude}")
    public Cidade porCoordenadas(@PathVariable String latitude, @PathVariable String longitude) {
        return cidadeRepositorio.buscaPorCoordenadas(latitude, longitude);
    }


    @PostMapping("/salvar")
    public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade, HttpServletResponse response) {
        try {
            Cidade cidadeCadastro = cidadeRepositorio.save(cidade);
            URI uri = ServletUriComponentsBuilder.
                    fromCurrentServletMapping().path("/{id}").
                    buildAndExpand(cidadeCadastro.getId()).toUri();
            return ResponseEntity.created(uri).body(cidadeCadastro);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

