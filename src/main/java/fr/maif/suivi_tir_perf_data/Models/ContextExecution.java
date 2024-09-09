package fr.maif.suivi_tir_perf_data.Models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class ContextExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pansiId")
    private PanSI panSI;

    private String environment;

    private String complementaryInformation;

    @ManyToOne
    @JoinColumn(name = "tirPerfId")
    private TirPerf tirPerf;

}
