package br.com.ctagroapi.ctagroapi.modules.poligono.repository;

import br.com.ctagroapi.ctagroapi.modules.poligono.model.Poligono;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoligonoRepository extends JpaRepository<Poligono, Integer> {
}
