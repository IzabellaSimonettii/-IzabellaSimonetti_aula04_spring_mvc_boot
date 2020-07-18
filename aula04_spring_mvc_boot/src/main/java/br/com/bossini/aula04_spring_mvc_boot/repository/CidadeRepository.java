package br.com.bossini.aula04_spring_mvc_boot.repository;

import br.com.bossini.aula04_spring_mvc_boot.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    public List<Cidade> buscaPorNome(String buscar);
    public Cidade buscaPorCoordenadas(String latitude, String longitude);
}