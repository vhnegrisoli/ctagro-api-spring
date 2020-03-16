package br.com.ctagroapi.ctagroapi.modules.poligono.model;

import br.com.ctagroapi.ctagroapi.modules.poligono.dto.PoligonoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Slf4j
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POLIGONO")
@SuppressWarnings("MemberName")
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

    public Poligono(Integer id) {
        this.id = id;
    }

    public static Poligono of(PoligonoResponse response) {
        var mapper = new ObjectMapper();
        var stringJson = "";
        try {
            stringJson = mapper.writeValueAsString(response.getGeo_json());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return Poligono
            .builder()
            .poligonoId(response.getId())
            .userId(response.getUser_id())
            .name(response.getName())
            .geoJson(stringJson)
            .area(response.getArea())
            .center(response.getCenter().toString())
            .build();
    }
}
