package fr.maif.suivi_tir_perf_data.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Scenario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "applicatif_id")
    private Applicatif applicatif;

    @ManyToMany
    @JoinTable(
            name = "scenario_fonction",
            joinColumns = {
                    @JoinColumn(name = "scenario_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "fonction_id")
            }
    )
    private List<Fonction> fonctions;
}
