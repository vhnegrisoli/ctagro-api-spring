package br.com.ctagroapi.ctagroapi.modules.poligono.repository;

import br.com.ctagroapi.ctagroapi.modules.poligono.model.Poligono;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PoligonoRepository extends JpaRepository<Poligono, Integer> {

    Optional<Poligono> findByPoligonoId(String poligonoId);
}
