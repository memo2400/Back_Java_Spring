package gm.rh.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;      // con ello se crea la tabla bd en autmatico
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;                     // para crear los get y setters automarticos.
import lombok.NoArgsConstructor;        // consturctor sin argumentos
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Posicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // se genera en automatico la llave primaria.
    
    Integer idPosicion;
    String instrumento;
    String plazo;
    LocalDate fechaRegistro;
    LocalDate fechaSubasta;

    LocalDate fechaFinal;
    Boolean reinversion;
    Double monto;
    Double isrRetenido;
    Double rendimiento;

    // Afuerza hay que usar este tipo de nombre sin "_" para que no falle la JPA Convention
    Boolean largoPlazo;
    LocalDateTime fechaCapturaFront;

    // @Column(columnDefinition = "JSON") // MySQL lo tratará como tipo JSON // no funciono
    // private String modificaciones;
    // String modificaciones;   // no funciono
    // private List<Modificacion> modificaciones; // no funciono  usando el la clase Modificacion

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "posicion_id") // Esto genera una relación unidireccional
    private List<Modificacion> modificaciones;


    // LocalDateTime fechaServer;
    // @PrePersist
    // public void prePersist() {
    //     this.fechaServer = LocalDateTime.now(); // Asignar la fecha y hora actual
    // }

     
    // Instrumento 	Plazo 	Registro 	Dia de Subasta 	Fecha Final 	Reinversion 	Monto 	ISR Ret 	Rendimiento %
}
