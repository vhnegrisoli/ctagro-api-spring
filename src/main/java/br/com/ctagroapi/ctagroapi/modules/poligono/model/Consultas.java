package br.com.ctagroapi.ctagroapi.modules.poligono.model;

import br.com.ctagroapi.ctagroapi.modules.poligono.enums.ETipoConsulta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONSULTAS")
public class Consultas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_POLIGONO", nullable = false)
    private Poligono poligono;

    @Column(name = "CONSULTA_JSON", nullable = false, length = 20000)
    private String consultaJson;

    @Column(name = "TIPO_CONSULTA", nullable = false)
    @Enumerated(EnumType.STRING)
    private ETipoConsulta tipoConsulta;

    @Column(name = "DATA_CADASTRO", nullable = false)
    private LocalDateTime dataCadastro;

    @PrePersist
    public void prePersist() {
        dataCadastro = LocalDateTime.now();
    }

    public static Consultas of(Integer poligonoId, ETipoConsulta tipoConsulta, String consultaJson) {
        return Consultas
            .builder()
            .tipoConsulta(tipoConsulta)
            .consultaJson(consultaJson)
            .poligono(new Poligono(poligonoId))
            .build();
    }
}
