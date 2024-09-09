package fr.maif.suivi_tir_perf_data.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class RapportExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Integer callsNumber;

    private Integer successRate;

    private String errors;

    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "tirPerf_id")
    private TirPerf tirPerf;
}
