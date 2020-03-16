package br.com.ctagroapi.ctagroapi.modules.poligono.model;

import br.com.ctagroapi.ctagroapi.modules.poligono.dto.PoligonoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POLIGONO")
public class Poligono {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "POLYGON_ID", nullable = false)
    private String poligonoId;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "NAME", nullable = false, length = 120)
    private String name;

    @Column(name = "GEO_JSON", nullable = false, length = 1000)
    private String geoJson;

    @Column(name = "CENTER", nullable = false)
    private String center;

    @Column(name = "AREA", nullable = false)
    private Double area;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public static Poligono of(PoligonoRequest request) {
        var poligono = new Poligono();
        BeanUtils.copyProperties(request, poligono);
        return poligono;
    }
}
