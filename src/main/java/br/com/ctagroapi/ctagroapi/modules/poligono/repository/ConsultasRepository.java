package br.com.ctagroapi.ctagroapi.modules.poligono.repository;

import br.com.ctagroapi.ctagroapi.modules.poligono.model.Consultas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultasRepository extends JpaRepository<Consultas, Integer> {

    List<Consultas> findByPoligonoId(Integer poligonoId);
}
