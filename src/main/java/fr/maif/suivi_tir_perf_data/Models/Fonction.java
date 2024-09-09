package fr.maif.suivi_tir_perf_data.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class Fonction {
     @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "fonctions")
    private List<Scenario> scenario;
}
