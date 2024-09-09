package fr.maif.suivi_tir_perf_data.Models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class Applicatif {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "fonction_id")
    private Fonction fonction;
}
