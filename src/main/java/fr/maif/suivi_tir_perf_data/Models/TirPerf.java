package fr.maif.suivi_tir_perf_data.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
@Table(name = "tir_perf")
public class TirPerf {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "creation_date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;
}
